package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionResDTO.GetMissionListDTO> getMissionList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "status") MissionStatus status
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_FOUND, missionService.getMissionList(memberId, status));
    }

    @PatchMapping("/{missionId}")
    public ApiResponse<MissionResDTO.UpdateMissionStatusResultDTO> completeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestParam(name = "memberId") Long memberId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_STATUS_UPDATED, missionService.updateMissionCompleted(memberId, missionId));
    }
}
