package umc.server.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.mission.service.MissionService;
import umc.server.domain.store.dto.request.MissionReqDTO;
import umc.server.domain.store.dto.response.MissionResDTO;
import umc.server.domain.store.enums.MissionSuccessCode;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreMissionController {

    private final MissionService missionService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<Void> createStoreMission(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionReqDTO.CreateMission request
    ) {
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        missionService.createMission(storeId, request);
        return ApiResponse.onSuccess(code);
    }

    @GetMapping("/{storeId}/missions")
    public ApiResponse<List<MissionResDTO.GetMission>> getStoreMissions(
            @PathVariable Long storeId
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId));
    }
}
