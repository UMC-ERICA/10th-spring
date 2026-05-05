package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.MemberRequestDTO;
import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody MemberRequestDTO.JoinDTO request) {
        // 서비스 로직 호출 부분 (현재는 생략)
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }
}
