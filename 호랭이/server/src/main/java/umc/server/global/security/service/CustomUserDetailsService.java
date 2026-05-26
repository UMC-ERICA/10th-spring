package umc.server.global.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.SocialType;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.global.apiPayload.exception.ProjectException;
import umc.server.global.security.entity.AuthMember;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username
    )throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(()-> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }

    public UserDetails loadUserByUidAndSocialType(
            SocialType socialType,
            String username
    ) throws UsernameNotFoundException {
        //db에서 기존 정보 조회, 인증 객체 생성
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, username)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new AuthMember(member);

    }
}
