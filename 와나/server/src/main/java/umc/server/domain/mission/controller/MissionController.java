package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.entity.enums.MemberMissionStatus;
import umc.server.domain.mission.entity.enums.MissionStatus;
import java.util.List;
import umc.server.domain.mission.dto.GetMissionsCountResponse;
import umc.server.domain.mission.dto.GetMissionsResponse;
import umc.server.domain.mission.service.MissionQueryService;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.exception.apiPayload.ApiResponse;
import umc.server.global.exception.code.CommonSuccessCode;
import umc.server.global.paging.OffsetPageResponse;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final MissionQueryService missionQueryService;

    // 미션 목록 조회 (진행중/종료)
    @GetMapping("/missions")
    public ApiResponse<List<GetMissionsResponse>> getMissions(
            @RequestParam MissionStatus missionStatus
    ) {
        return ApiResponse.success(CommonSuccessCode.OK, missionQueryService.getMissions(missionStatus));
    }


    // 홈화면 - 내 미션 조회 API
    @GetMapping("/missions/my")
    public ApiResponse<OffsetPageResponse<GetMissionsResponse>> getMyMission(
            @RequestParam MemberMissionStatus memberMissionStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // 추후 Security를 통해 자신의 memberId를 가져옴. 지금은 임시로 1L 사용
        Long memberId = 1L;
        return ApiResponse.success(CommonSuccessCode.OK, missionQueryService.getMyMissions(memberId, memberMissionStatus, page, size));
    }

    // 홈화면 - 완료 미션 개수
    @GetMapping("/missions/my/count")
    public ApiResponse<GetMissionsCountResponse> getMissionCount(
            @RequestParam MemberMissionStatus memberMissionStatus
    ) {
        // 추후 Security를 통해 자신의 memberId를 가져옴. 지금은 임시로 1L 사용
        Long memberId = 1L;
        return ApiResponse.success(CommonSuccessCode.OK, missionQueryService.getMyMissionCount(memberId, memberMissionStatus));
    }

    // 미션 성공 누르기
    @PatchMapping("/missions/{missionId}/success")
    public ApiResponse<Object> completeMission(
            @PathVariable Long missionId
    ) {
        // 추후 Security를 통해 자신의 memberId를 가져옴. 지금은 임시로 1L 사용
        Long memberId = 1L;
        missionService.completeMission(memberId, missionId);
        return ApiResponse.success(CommonSuccessCode.OK, null);
    }
}
