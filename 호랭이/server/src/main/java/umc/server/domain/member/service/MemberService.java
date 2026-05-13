package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    public String singleParameter(String queryParameter) {
        return queryParameter;
    } //테스트용

    public MemberResDTO.MemberInfo createMember(MemberReqDTO.CreateMember request){
        Member member = MemberConverter.toMember(request);
        Member saved = memberRepository.save(member);
        return MemberConverter.toMemberInfo(saved);
    }

}