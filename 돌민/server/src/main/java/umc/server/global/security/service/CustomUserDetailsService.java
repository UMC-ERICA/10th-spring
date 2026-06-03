package umc.server.global.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.MemberErrorCode;
import umc.server.domain.member.enums.SocialType;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.global.security.entity.AuthMember;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUidAndSocialType(SocialType socialType, String username) throws UsernameNotFoundException {
        Member member = memberRepository.findSocialTypeandSocialUid(socialType, username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return new AuthMember(member);
    }

}
