package umc.server.domain.member.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.server.domain.common.entity.Address;
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
    private final PasswordEncoder passwordEncoder;

    public MemberResDTO.GetInfo getMe(){

        Member memberMe = memberRepository.findById(1L).orElseThrow(); // 추후에 유저ID로 변

        return MemberConverter.toGetInfoResult(memberMe);
    };

    public MemberResDTO.GetAddress getAddress(){

        Member member = memberRepository.findById(1L).orElseThrow();

        Address address = member.getAddress();

        return MemberConverter.toGetAddressResult(address);
    }

    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp dto) {

        if(memberRepository.existsByEmail(dto.email())){
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        String encodePassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodePassword);

        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUpResultDTO(savedMember);
    }
}
