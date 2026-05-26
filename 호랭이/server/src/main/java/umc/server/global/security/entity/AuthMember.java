package umc.server.global.security.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import umc.server.domain.member.entity.Member;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }

    @Override
    public @NotNull String getPassword(){
        return member.getPassword(); //member entity
    }

    @Override
    public String getUsername(){ //로그인 식별자를 반환
        return member.getEmail(); // 이름은 중복이니까 이메일로
    }


}
