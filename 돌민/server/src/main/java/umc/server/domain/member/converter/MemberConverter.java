package umc.server.domain.member.converter;

import umc.server.domain.member.dto.request.MemberSignUpRequest;
import umc.server.domain.member.entity.Member;
import umc.server.global.security.dto.OAuthDTO;
import umc.server.domain.member.entity.MemberAddress;
import umc.server.domain.member.entity.MemberTermCondition;
import umc.server.domain.member.entity.PreferenceFood;
import umc.server.domain.member.entity.TermCondition;
import umc.server.domain.member.enums.FoodCategory;
import umc.server.domain.member.enums.SocialType;

public class MemberConverter {

    public static Member toMember(MemberSignUpRequest request, String encodedPassword) {
        return Member.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .phone(request.phone())
                .gender(request.gender())
                .birth(request.birth())
                .socialType(SocialType.LOCAL)
                .socialUid(request.email())       // LOCAL은 email을 socialUid로 사용
                .build();
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .password("")
                .phone("")
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }

    public static MemberAddress toMemberAddress(MemberSignUpRequest request, Member member) {
        return MemberAddress.builder()
                .city(request.city())
                .district(request.district())
                .dong(request.dong())
                .detailAddress(request.detailAddress())
                .member(member)
                .build();
    }

    public static PreferenceFood toPreferenceFood(FoodCategory category, Member member) {
        return PreferenceFood.builder()
                .preferenceFoodCategory(category)
                .member(member)
                .build();
    }

    public static MemberTermCondition toMemberTermCondition(
            TermCondition termCondition, Boolean isAgreed, Member member) {
        MemberTermCondition mtc = MemberTermCondition.builder()
                .termCondition(termCondition)
                .isAgreed(isAgreed)
                .member(member)
                .build();

        // 엔티티 내부 메서드로 동의/거부 시각 기록
        if (isAgreed) {
            mtc.agree();
        } else {
            mtc.reject();
        }
        return mtc;
    }
}
