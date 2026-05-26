package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;
import umc.server.global.common.entity.Region;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

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
