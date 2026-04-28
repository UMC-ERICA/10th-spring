package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members") // 도메인에 맞게 경로 세분화
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/me")
    public ApiResponse<MemberResDTO.MemberInfoDTO> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ) {

        return ApiResponse.onSuccess(memberService.getInfo(dto));
    }


    @GetMapping("/query-parameter")
    public ApiResponse<String> testQueryParameter(
            @RequestParam(name = "query") String queryParameter
    ) {
        return ApiResponse.onSuccess(memberService.singleParameter(queryParameter));
    }


    @PostMapping("/request-body")
    public ApiResponse<MemberResDTO.RequestBodyResultDTO> testRequestBody(
            @RequestBody MemberReqDTO.RequestBody dto
    ) {
        return ApiResponse.onSuccess(memberService.requestBody(dto));
    }


    @GetMapping("/{pathVariable}")
    public ApiResponse<String> testPathVariable(
            @PathVariable(name = "pathVariable") String pathVariable
    ) {
        return ApiResponse.onSuccess(memberService.singleParameter(pathVariable));
    }


    @GetMapping("/header")
    public ApiResponse<String> testHeader(
            @RequestHeader(name = "test") String test
    ) {
        return ApiResponse.onSuccess(memberService.singleParameter(test));
    }


    @GetMapping("/test")
    public ApiResponse<String> test() {
        return ApiResponse.onSuccess("test success");
    }
}