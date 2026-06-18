package umc.server.global.security.dto;

import umc.server.domain.member.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();

    String getSocialUid();

    String getSocialEmail();

    String getName();

}
