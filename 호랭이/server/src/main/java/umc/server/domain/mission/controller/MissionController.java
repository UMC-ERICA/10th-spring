package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<List<MissionResDTO.MissionStatusList>> getMissions(
            @RequestParam(required = false) MissionStatus status
    ){
        return ApiResponse.isSuccess(
                missionService.getMissions(status)
        );
    }
}
