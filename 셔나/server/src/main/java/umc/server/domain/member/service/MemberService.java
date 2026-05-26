package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberFood;
import umc.server.domain.member.entity.TermsAgreed;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.FoodErrorCode;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.global.security.entity.CustomUserDetails;
import umc.server.global.security.util.JwtUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MemberResDTO.JoinResultDTO join(MemberReqDTO.JoinDTO request) {
        // 아이디 중복 체크
        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // Member 엔티티 생성
        Member member = MemberConverter.toMember(request, encodedPassword);

        // 약관 동의 연관관계 설정
        TermsAgreed termsAgreed = MemberConverter.toTermsAgreed(request.terms());
        member.setTermsAgreed(termsAgreed);

        // 음식 취향(MemberFood) 연관관계 설정
        List<MemberFood> memberFoodList = request.memberPreferFoodIds().stream()
                .map(foodId -> {
                    Food food = foodRepository.findById(foodId)
                            .orElseThrow(() -> new MemberException(FoodErrorCode.FOOD_NOT_FOUND));

                    return MemberFood.builder()
                            .member(member)
                            .food(food)
                            .build();
                }).toList();

        member.getMemberFoodList().addAll(memberFoodList);

        Member savedMember = memberRepository.save(member);

        return MemberConverter.toJoinResultDTO(savedMember);
    }

    public MemberResDTO.LoginResultDTO login(MemberReqDTO.LoginDTO request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        CustomUserDetails userDetails = new CustomUserDetails(member);
        String accessToken = jwtUtil.createAccessToken(userDetails);

        return MemberConverter.toLoginResultDTO(member, accessToken);
    }

    public MemberResDTO.GetProfileResultDTO getProfile(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetProfileResultDTO(member);
    }
}
