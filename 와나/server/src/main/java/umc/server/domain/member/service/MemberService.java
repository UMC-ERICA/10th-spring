package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.dto.request.SignupRequest;
import umc.server.domain.member.dto.response.GetProfileResponse;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberAddr;
import umc.server.domain.member.entity.enums.MemberRole;
import umc.server.domain.member.entity.enums.MemberStatus;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberAddrRepository;
import umc.server.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberAddrRepository memberAddrRepository;
    private final PasswordEncoder passwordEncoder;


    public Long signUp(SignupRequest request) {
        if (memberRepository.findByEmail(request.email()).isPresent()) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        Member member = Member.builder()
                .name(request.memberName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .gender(request.gender())
                .birth(request.birthDate())
                .point(0L)
                .status(MemberStatus.ACTIVE)
                .role(MemberRole.USER)
                .authentificatedPhone(false)
                .build();

        Member saved = memberRepository.save(member);

        if (request.memberAddr() != null) {
            MemberAddr addr = MemberAddr.create(
                    saved,
                    request.memberAddr().city(),
                    request.memberAddr().sigungu(),
                    request.memberAddr().dongeupmyun(),
                    request.memberAddr().street(),
                    request.memberAddr().building()
            );
            memberAddrRepository.save(addr);
        }

        return saved.getId();
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
