package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    @GetMapping
    public ApiResponse<MissionResDTO.GetMissionList> getMissionList(
            @RequestParam("isCompleted") Boolean isCompleted
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,null);
    }

    @GetMapping("/region")
    public ApiResponse<MissionResDTO.GetProgress> getProgress(
            @RequestParam Long addressId
    ){
        BaseSuccessCode code = MissionSuccessCode.PROGRESS_FOUND;
        return ApiResponse.onSuccess(code,null);
    }

    @PatchMapping("/{member-mission-id}/complete")
    public ApiResponse<MissionResDTO.Complete> complete(
            @PathVariable("member-mission-id") Long mmId
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETE;
        return ApiResponse.onSuccess(code,null);
    }

}
