package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.global.security.entity.AuthMember;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public String singleParameter(String queryParameter) {
        return queryParameter;
    } //테스트용

    public MemberResDTO.GetInfo createMember(MemberReqDTO.CreateMember request){
        Member member = MemberConverter.toMember(request, passwordEncoder.encode(request.pw()));
        Member saved = memberRepository.save(member);
        return MemberConverter.toGetInfo(saved);
    }

    public MemberResDTO.GetInfo getInfo(AuthMember member){
        //컨버터로 응답 DTO 생성
        return MemberConverter.toGetInfo(member.getMember());
    }

}