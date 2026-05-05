package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO.JoinResDTO;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    @PostMapping("/signup")
    public ApiResponse<JoinResDTO> join(@RequestBody MemberReqDTO.JoinReqDTO dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }

}
