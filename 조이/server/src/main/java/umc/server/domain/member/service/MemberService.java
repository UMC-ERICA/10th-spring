package umc.server.domain.member.service;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional

public class MemberService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public MemberResDTO.JoinResDTO join(MemberReqDTO.JoinReqDTO dto) {
        // 1. 회원 가입 로직 구현
        Member member = MemberConverter.toMemberEntity(dto);
        // 2. 회원 정보 저장 (음식 관계 설정 추후 구현 하겠습니다)

        return MemberConverter.toMemberDTO(memberRepository.save(member));
    }

    // 마이페이지 조회
    public MemberResDTO.MyPageResDTO myPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPageDTO(member);
    }

}
