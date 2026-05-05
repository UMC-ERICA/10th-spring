package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.GetInfo getMe(){

        Member memberMe = memberRepository.findById(1L).orElseThrow(); // 추후에 유저ID로 변

        return MemberConverter.toGetInfoResult(memberMe);
    };
}
