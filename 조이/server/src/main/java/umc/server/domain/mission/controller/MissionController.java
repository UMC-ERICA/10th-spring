package umc.server.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.enums.Status;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.mission.service.MissionCommandService;
import umc.server.domain.mission.service.MissionQueryService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    //내 미션리스트 조회
    @GetMapping
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MissionsResDTO>> getMissionList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort,
            @RequestParam(name = "status") Status status
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,
                missionQueryService.getMyMission(memberId, pageSize, pageNumber, status, sort));
    }

    //미션 성공
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionsResDTO> getMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestParam(name = "memberId") Long memberId //보안위험이있어서 나중에 토큰 처리를 해야함!!!
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionCommandService.completeMission(memberId, missionId));
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
        return ApiResponse.onSuccess(code, missionQueryService.getMissions(storeId, pageSize, pageNumber, sort));
    }

    //가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionCommandService.createMission(storeId, dto));
    }

}
