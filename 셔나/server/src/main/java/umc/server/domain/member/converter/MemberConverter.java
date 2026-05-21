package umc.server.domain.member.converter;

import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.TermsAgreed;

import java.util.ArrayList;

public class MemberConverter {

    // DTO -> Member entity
    public static Member toMember(MemberReqDTO.JoinDTO request, String encodedPassword) {
        return Member.builder()
                .email(request.email())
                .password(encodedPassword)
                .username(request.username())
                .gender(request.gender())
                .birth(request.birth())
                .addressZipcode(request.addressZipcode())
                .locationName(request.locationName())
                .addressDetails(request.addressDetails())
                .memberFoodList(new ArrayList<>())
                .build();
    }

    // DTO -> TermsAgreed entity
    public static TermsAgreed toTermsAgreed(MemberReqDTO.TermsDTO request) {
        return TermsAgreed.builder()
                .ageOver14(request.ageOver14())
                .serviceTerms(request.serviceTerms())
                .privacyTerms(request.privacyTerms())
                .locationTerms(request.locationTerms())
                .marketingTerms(request.marketingTerms())
                .build();
    }

    // entity -> 회원가입 DTO
    public static MemberResDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // entity -> 회원 프로필 조회 DTO
    public static MemberResDTO.GetProfileResultDTO toGetProfileResultDTO(Member member) {
        return MemberResDTO.GetProfileResultDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .totalPoints(member.getTotalPoints())
                .build();
    }
}
