package umc.server.domain.member.converter;

import umc.server.domain.member.dto.request.MemberSignUpRequest;
import umc.server.domain.member.entity.Member;
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
                .password(encodedPassword)        // 암호화된 비밀번호 저장
                .phone(request.phone())
                .gender(request.gender())
                .birth(request.birth())
                .socialType(SocialType.LOCAL)     // 이메일 회원가입 = 로컬(소셜 아님)
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
