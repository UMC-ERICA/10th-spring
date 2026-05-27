package umc.server.global.security.DTO;

import lombok.AllArgsConstructor;
import umc.server.domain.member.enums.SocialType;

@AllArgsConstructor
public class KakaoDTO implements OAuthDTO{

    private final String id;
    private final String email;
    private final String name;

    @Override
    public SocialType getSocialType(){
        return SocialType.KAKAO;
    }

    @Override
    public String getSocialUid(){
        return id;
    }

    @Override
    public String getSocialEmail(){
        return email;
    }

    @Override
    public String getName(){
        return name;
    }
}
