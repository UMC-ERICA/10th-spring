package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.dto.request.LoginRequest;
import umc.server.domain.member.dto.request.SignupRequest;
import umc.server.domain.member.dto.response.LoginResponse;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberAddr;
import umc.server.domain.member.entity.enums.MemberRole;
import umc.server.domain.member.entity.enums.MemberStatus;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberAddrRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.global.security.entity.AuthMember;
import umc.server.global.security.util.JwtUtil;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final MemberAddrRepository memberAddrRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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

    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return new LoginResponse(accessToken);
    }
}
