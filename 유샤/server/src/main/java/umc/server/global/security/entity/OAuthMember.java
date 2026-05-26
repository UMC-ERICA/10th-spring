package umc.server.global.security.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import umc.server.domain.member.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class OAuthMember implements OAuth2User {

    @Getter
    private final Member member;
    private final Map<String,Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return member.getSocialUid();
    }
}
