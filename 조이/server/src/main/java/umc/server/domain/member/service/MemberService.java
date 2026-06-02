package umc.server.domain.member.service;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.global.security.entity.AuthMember;
import umc.server.global.security.service.CustomUserDetailsService;
import umc.server.global.security.util.JwtUtil;

@Service
@RequiredArgsConstructor
@Transactional

public class MemberService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public MemberResDTO.JoinResDTO join(MemberReqDTO.JoinReqDTO dto) {
        // 1. 비밀번호 BCrypt 인코딩
        String encodedPassword = passwordEncoder.encode(dto.password());
        // 2. 회원 엔티티 생성 및 저장 (음식 관계 설정 추후 구현 하겠습니다!!!)
        Member member = MemberConverter.toMemberEntity(dto, encodedPassword);

        return MemberConverter.toMemberDTO(memberRepository.save(member));
    }

    // 로그인
    public MemberResDTO.LoginResDTO login(MemberReqDTO.LoginReqDTO dto) {
        // 1. 이메일로 회원 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // 3. JWT 발급
        AuthMember authMember = (AuthMember) customUserDetailsService.loadUserByUsername(member.getEmail());
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberResDTO.LoginResDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    // 마이페이지 조회
    public MemberResDTO.MyPageResDTO getInfo(AuthMember member) {

        return MemberConverter.toMyPageDTO(member.getMember());
    }

}
