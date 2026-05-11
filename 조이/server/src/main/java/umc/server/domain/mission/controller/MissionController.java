package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.enums.Status;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionService missionService;

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

    //가게 내 미션 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MissionsResDTO>> getStoreMissionList(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }

    //가게 미션 생성
//    @PatchMapping("/stores/{storeId}/missions")
//    public ApiResponse<void> createMission(
//            @PathVariable Long storeId,
//            @RequestParam MissionReqDTO.CreateMission dto
//    ) {
//        BaseSuccessCode code = MissionSuccessCode.CREATED;
//        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
//    }

}
