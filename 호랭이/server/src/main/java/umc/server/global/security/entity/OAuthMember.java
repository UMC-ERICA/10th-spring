package umc.server.global.security.entity;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import umc.server.domain.member.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class OAuthMember implements OAuth2User {

    private final Member member;
    private final Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes(){
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }


    public @Nullable String getPassword(){
        return null;
    }


    public String getUsername(){
        return member.getSocialUid();
    }

    @Override
    public String getName(){
        return member.getName();
    }

}
