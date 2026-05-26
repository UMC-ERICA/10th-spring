package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberRequestDTO;
import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;
import umc.server.global.common.entity.Address;
import umc.server.global.common.entity.Region;
import umc.server.global.common.repository.AddressRepository;
import umc.server.global.security.AuthMember;
import umc.server.global.security.util.JwtUtil;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResponseDTO.JoinResultDTO join(MemberRequestDTO.JoinDTO request) {
        // 이메일 중복 확인
        memberRepository.findByEmail(request.email())
                .ifPresent(m -> {
                    throw new GeneralException(GeneralErrorCode.MEMBER_ALREADY_EXISTS);
                });

        Address address = addressRepository.findById(request.addressId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.BAD_REQUEST));

        String encodedPassword = passwordEncoder.encode(request.password());
        Member member = MemberConverter.toMember(request, encodedPassword, address);

        return MemberConverter.toJoinResultDTO(memberRepository.save(member));
    }

    public MemberResponseDTO.LoginResultDTO login(MemberRequestDTO.LoginDTO request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST); // TODO: 비밀번호 불일치 에러코드 필요
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberConverter.toLoginResultDTO(accessToken);
    }

    public MemberResponseDTO.MyPageDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toMyPageDTO(member);
    }

    public MemberResponseDTO.HomeDTO getMemberHome(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        Region region = member.getAddress().getRegion();
        Page<Mission> missionPage = missionRepository.findAllByStoreAddressRegion(region, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt")));

        Integer missionProgress = getMissionProgress(member);

        return MemberConverter.toHomeDTO(member, missionPage, missionProgress);
    }

    public Member getMyPageInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));
    }

    public Page<Mission> getHomeMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        Region region = member.getAddress().getRegion();
        return missionRepository.findAllByStoreAddressRegion(region, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    public Integer getMissionProgress(Member member) {
        return member.getCompletedMissionCount() % 10;
    }
}
