package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService; //스프링이 자동주입

    @PostMapping("/")
    public ApiResponse<MemberResDTO.MemberInfo> createMember(
            @RequestBody@Valid MemberReqDTO.CreateMember request
    ){
        BaseSuccessCode code = GeneralSuccessCode.CREATED;
        return ApiResponse.onSuccess(code,memberService.createMember(request));
    }
    @GetMapping("/query-parameter")
    public ApiResponse<String> testQueryParameter(
            @RequestParam(name = "query") String queryParameter //url 뒤에 붙는 파라미터 값
    ) {
        return ApiResponse.onSuccess(memberService.singleParameter(queryParameter));
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


    @GetMapping("/test") //서버 살아있는지 확인
    public ApiResponse<String> test() {
        return ApiResponse.onSuccess("test success");

    }

}