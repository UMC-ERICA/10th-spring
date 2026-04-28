package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.MemberRequestDTO;
import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody MemberRequestDTO.JoinDTO request) {
        // 서비스 로직 호출 부분 (현재는 생략)
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
