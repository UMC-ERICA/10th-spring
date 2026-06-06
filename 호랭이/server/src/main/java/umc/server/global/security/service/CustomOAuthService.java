package umc.server.global.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.SocialType;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.global.security.DTO.KakaoDTO;
import umc.server.global.security.DTO.OAuthDTO;
import umc.server.global.security.entity.OAuthMember;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest
    ) throws OAuth2AuthenticationException{
        //일회성 토큰을 이용해 정보 조회, 유저 객체 생성
        OAuth2User oAuthMember = super.loadUser(userRequest);

        //유저 객체에서 정보 추출
        SocialType providerId;
        String socialUid;
        Map<String, Object> attributes = oAuthMember.getAttributes();
        Map<String, Object> KakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) KakaoAccount.get("profile");
        try{
            providerId = SocialType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
            socialUid = String.valueOf((Long) oAuthMember.getAttributes().get("id"));
        } catch (IllegalArgumentException e){
            throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVICER);
        }

        //OAuth 공통 정보 DTO로 매핑
        OAuthDTO dto;
        switch (providerId){ //값에 따라 분기
            case  KAKAO -> {
                String email = KakaoAccount.get("email").toString();
                String name = profile.get("nickname").toString();
                dto = new KakaoDTO(socialUid, email, name);
            }
            default -> throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVICER);
        }

        //db 저장
        Member member = memberRepository.findBySocialTypeAndSocialUid(providerId, socialUid)
                .orElseGet(() -> {
            Member newMember = MemberConverter.toMember(dto);
            memberRepository.save(newMember);
            return newMember;
        });
    return new OAuthMember(member, oAuthMember.getAttributes());


    }


}
