package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionResponseDTO;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping("/users/me/missions")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMyMissionList(
            @RequestParam(name = "status") MissionStatus status,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {

        // TODO: 로그인된 사용자의 ID를 가져오는 로직 (현재는 임시값 1L 사용)
        Long memberId = 1L;

        Page<MemberMission> memberMissionPage = missionService.getMemberMissionList(memberId, status, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MissionConverter.toMissionListDTO(memberMissionPage));
    }

    // 미션 완료 처리
    @PatchMapping("/user-missions/{userMissionId}/complete")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "userMissionId") Long userMissionId) {

        // TODO: 서비스 로직 연결은 추후 진행
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
