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
    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfo dto){

        Long memberId = dto.id();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    public String singleParameter(
            String singleParameter
    ){
        return singleParameter;
    }

    public MemberResDTO.RequestBody requestBody(
            MemberReqDTO.RequestBody dto
    ){
        return MemberConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }
}
