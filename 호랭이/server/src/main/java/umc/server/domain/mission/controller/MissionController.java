package umc.server.domain.mission.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    //미션 생성
    @PostMapping("v1/store/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionResDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code,missionService.createMission(storeId, dto));
    }

    //가게 미션 조회
    @GetMapping("/v1/store/{storeId}/missions")
    public ApiResponse<MissionResDTO.CursorPage<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,
                missionService.getMissions(storeId,pageSize,cursor,query));
    }

    //미션 목록 조회
    @PostMapping("/v1/users/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMyMission>>getMyMissions(
            @RequestBody MissionReqDTO.GetMyMission request,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam String status,
            @RequestParam(required = false) String sort
            ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.getMyMissions(request.userId(),pageSize,pageNumber,status,sort));

    }
}
