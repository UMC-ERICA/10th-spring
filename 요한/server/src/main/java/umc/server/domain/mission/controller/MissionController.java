package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionResponseDTO;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    // 미션 목록 조회
    @GetMapping("/users/me/missions")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMyMissions(
            @RequestParam(name = "status") MissionStatus status) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    // 미션 완료 처리
    @PatchMapping("/user-missions/{userMissionId}/complete")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "userMissionId") Long userMissionId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
