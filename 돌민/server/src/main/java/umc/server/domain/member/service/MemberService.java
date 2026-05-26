package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.inquiry.repository.InquiryRepository;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.request.HomeSearchRequest;
import umc.server.domain.member.dto.request.MemberSignUpRequest;
import umc.server.domain.member.dto.response.HomeResponse;
import umc.server.domain.member.dto.response.MemberLocationResponse;
import umc.server.domain.member.dto.response.MemberPointResponse;
import umc.server.domain.member.dto.response.MissionHomeResponse;
import umc.server.domain.member.dto.response.MyPageResponse;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberAddress;
import umc.server.domain.member.entity.MemberTermCondition;
import umc.server.domain.member.entity.PreferenceFood;
import umc.server.domain.member.entity.TermCondition;
import umc.server.domain.member.enums.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberAddressRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.member.repository.PreferenceFoodRepository;
import umc.server.domain.member.repository.TermConditionRepository;
import umc.server.domain.mission.dto.request.MemberMissionSearchRequest;
import umc.server.domain.mission.dto.response.MemberMissionResponse;
import umc.server.domain.mission.enums.MemberMissionStatus;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.notification.dto.response.CompletedMissionCountResponse;
import umc.server.domain.notification.repository.AlarmRepository;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.global.apiPayload.PageResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;
    private final InquiryRepository inquiryRepository;
    private final AlarmRepository alarmRepository;
    private final MissionRepository missionRepository;
    private final PasswordEncoder passwordEncoder;
    private final TermConditionRepository termConditionRepository;
    private final PreferenceFoodRepository preferenceFoodRepository;

    @Transactional
    public void signUp(MemberSignUpRequest request) {

        // 1. 비밀번호 BCrypt 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // 2. Member 저장
        Member member = memberRepository.save(MemberConverter.toMember(request, encodedPassword));

        // 3. 주소 저장
        MemberAddress address = MemberConverter.toMemberAddress(request, member);
        memberAddressRepository.save(address);

        // 4. 선호 음식 저장 (다중 선택)
        for (var category : request.preferredFoods()) {
            PreferenceFood pf = MemberConverter.toPreferenceFood(category, member);
            preferenceFoodRepository.save(pf);
        }

        // 5. 약관 동의 저장
        for (var agreement : request.termAgreements()) {
            TermCondition termCondition = termConditionRepository.findById(agreement.termConditionId())
                    .orElseThrow(() -> new RuntimeException("해당 약관이 존재하지 않습니다."));
            MemberTermCondition mtc = MemberConverter.toMemberTermCondition(termCondition, agreement.isAgreed(), member);
            member.addTermCondition(mtc);
        }
    }

    public MemberLocationResponse getMemberLocation(Long memberId) {
        validateMember(memberId);
        return memberAddressRepository.findLocationsByMemberId(memberId, PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .orElseThrow(() -> new MemberException(MemberErrorCode.ADDRESS_NOT_FOUND));
    }

    public MemberPointResponse getMemberPoint(Long memberId) {
        Member member = validateMember(memberId);
        return new MemberPointResponse(member.getId(), member.getPoint());
    }

    public CompletedMissionCountResponse getCompletedMissionCount(Long memberId) {
        validateMember(memberId);
        long completedMissionCount = memberMissionRepository.countByMemberIdAndStatus(memberId, MemberMissionStatus.COMPLETE);
        return new CompletedMissionCountResponse(memberId, completedMissionCount);
    }

    public PageResponse<MemberMissionResponse> getMemberMissions(
            Long memberId,
            MemberMissionSearchRequest request,
            Pageable pageable
    ) {
        validateMember(memberId);

        Page<MemberMissionResponse> page = memberMissionRepository.findMemberMissionsByMemberIdAndStatus(
                memberId,
                request.status(),
                pageable
        );
        return PageResponse.from(page);
    }

    public MyPageResponse getMyPage(Long memberId) {
        Member member = validateMember(memberId);
        long reviewCount = reviewRepository.countByMemberId(memberId);
        long inquiryCount = inquiryRepository.countByMemberId(memberId);

        return new MyPageResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                false,
                member.getPoint(),
                reviewCount,
                inquiryCount
        );
    }

    public HomeResponse getHome(Long memberId, HomeSearchRequest request, Pageable pageable) {
        Member member = validateMember(memberId);
        boolean hasNewAlarm = alarmRepository.existsUnreadAlarmByMemberId(memberId);
        long completedMissionCount = memberMissionRepository.countByMemberIdAndStatus(memberId, MemberMissionStatus.COMPLETE);
        Page<MissionHomeResponse> missions = missionRepository.findAvailableMissionsByRegion(
                memberId,
                request.getCity(),
                request.getDistrict(),
                request.getDong(),
                MissionStatus.READY,
                pageable
        );

        return new HomeResponse(
                member.getId(),
                request.getDong(),
                member.getPoint(),
                hasNewAlarm,
                completedMissionCount,
                PageResponse.from(missions)
        );
    }

    private Member validateMember(Long memberId) {
        return memberRepository.findActiveById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
