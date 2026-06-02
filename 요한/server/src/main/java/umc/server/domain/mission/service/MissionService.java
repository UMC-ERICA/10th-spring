package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionRequestDTO;
import umc.server.domain.mission.dto.MissionResponseDTO;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResponseDTO.MissionListDTO getMissionList(Long memberId, MissionRequestDTO.MissionListRequestDTO request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberAndStatus(
                member,
                request.status(),
                PageRequest.of(request.page(), request.pageSize(), Sort.by(Sort.Direction.DESC, "createdAt"))
        );

        return MissionConverter.toMissionListDTO(memberMissionPage);
    }
}
