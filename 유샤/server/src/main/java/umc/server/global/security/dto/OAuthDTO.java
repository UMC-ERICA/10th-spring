package umc.server.global.security.dto;

import umc.server.domain.member.enums.Provider;

public interface OAuthDTO {

    Provider getProvider();
    String getSocialUid();
    String getName();
    String getSocialEmail();
}
