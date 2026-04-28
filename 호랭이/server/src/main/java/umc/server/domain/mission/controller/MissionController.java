package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeView> getHome() {
        return ApiResponse.onSuccess(missionService.getHomeData());
    }

    @GetMapping("/missions/in_progress")
    public ApiResponse<MissionResDTO.MissionStatusList> getInProgress() {
        return ApiResponse.onSuccess(missionService.getMissions("in_progress"));
    }

    @GetMapping("/missions/completed")
    public ApiResponse<MissionResDTO.MissionStatusList> getCompleted() {
        return ApiResponse.onSuccess(missionService.getMissions("completed"));
    }

    @PatchMapping("/missions/{missionId}/complete")
    public ApiResponse<String> completeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(missionService.complete(missionId));
    }

    @PostMapping("/missions/{missionId}/reviews")
    public ApiResponse<String> createReview(
            @PathVariable Long missionId,
            @RequestBody String content
    ) {
        return ApiResponse.onSuccess(missionService.postReview(missionId, content));
    }
}