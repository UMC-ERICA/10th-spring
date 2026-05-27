package umc.server.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionRequestDTO;
import umc.server.domain.mission.dto.MissionResponseDTO;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회
    @PostMapping("/users/me/missions")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMyMissionList(
            @RequestBody @Valid MissionRequestDTO.MissionListRequestDTO request) {

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getMissionList(request));
    }

    // 미션 완료 처리
    @PatchMapping("/user-missions/{userMissionId}/complete")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "userMissionId") Long userMissionId) {

        // TODO: 서비스 로직 연결은 추후 진행
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
