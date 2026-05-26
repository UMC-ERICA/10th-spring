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
import umc.server.global.security.entity.AuthMember;
import umc.server.global.security.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MemberResDTO.GetInfo getMe(
            AuthMember member
    ){
        return MemberConverter.toGetInfoResult(member.getMember());
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

        // JWT
        AuthMember authMember = new AuthMember(savedMember);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberConverter.toSignUpResultDTO(savedMember,accessToken);
    }

    public MemberResDTO.Login login(MemberReqDTO.Login dto) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        if(!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}
