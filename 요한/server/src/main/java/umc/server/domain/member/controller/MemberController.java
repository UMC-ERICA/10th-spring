package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberRequestDTO;
import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.service.MemberService;
import umc.server.domain.mission.entity.Mission;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody MemberRequestDTO.JoinDTO request) {
        // TODO: 추후 구현
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }

    @GetMapping("/me")
    public ApiResponse<MemberResponseDTO.MyPageDTO> getMyPage() {
        // TODO: 로그인된 사용자의 ID를 가져온다고 가정 (1L)
        Long memberId = 1L;
        Member member = memberService.getMyPageInfo(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MemberConverter.toMyPageDTO(member));
    }

    @GetMapping("/me/home")
    public ApiResponse<MemberResponseDTO.HomeDTO> getHome(
            @RequestParam(name = "page") Integer page) {
        // TODO: 로그인된 사용자의 ID를 가져온다고 가정 (1L)
        Long memberId = 1L;
        Member member = memberService.getMyPageInfo(memberId);
        Page<Mission> missionPage = memberService.getHomeMissionList(memberId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MemberConverter.toHomeDTO(member, missionPage));
    }
}
