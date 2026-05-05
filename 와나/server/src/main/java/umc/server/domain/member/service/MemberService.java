package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.dto.request.SignupRequest;
import umc.server.domain.member.dto.response.GetProfileResponse;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Long signUp(SignupRequest request) {
        // TODO : 추후 구현
        return null;
    }

    public GetProfileResponse getProfile(Long memberId) {
        Member member = findMemberById(memberId);
        return GetProfileResponse.from(member);

    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
