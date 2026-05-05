package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.enums.Status;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    //내 미션리스트 조회
    @GetMapping
    public ApiResponse<MissionResDTO.MissionsGetResDTO> getMissionList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "status") Status status
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    //미션 성공
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionsResDTO> getMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestParam(name = "memberId") Long memberId //보안위험이있어서 나중에 토큰 처리를 해야함!!!
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
