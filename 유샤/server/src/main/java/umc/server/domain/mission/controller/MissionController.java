package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    public final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissionList(
            @RequestParam("memberId") Long memberId,
            @RequestParam("isCompleted") Boolean isCompleted,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        MissionResDTO.Pagination<MissionResDTO.GetMission> result = missionService.getMissionsPage(memberId, isCompleted, pageSize, pageNumber, sort);
        BaseSuccessCode code = MissionSuccessCode.OK;

        return ApiResponse.onSuccess(code,result);
    }

    @GetMapping("/region")
    public ApiResponse<MissionResDTO.GetProgress> getProgress(
            @RequestParam Long addressId
    ){
        MissionResDTO.GetProgress result = missionService.getProgress(addressId);
        BaseSuccessCode code = MissionSuccessCode.PROGRESS_FOUND;
        return ApiResponse.onSuccess(code,result);
    }

    @PatchMapping("/{member-mission-id}/complete")
    public ApiResponse<MissionResDTO.Complete> complete(
            @PathVariable("member-mission-id") Long mmId
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETE;
        return ApiResponse.onSuccess(code,null);
    }

}
