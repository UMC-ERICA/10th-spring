package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/missions")
public class MissionController {

    @GetMapping("/region")
    public ApiResponse<MissionResDTO.getProgress> getProgress(
            @RequestBody MissionReqDTO.getProgress dto
    ){
        BaseSuccessCode code = MissionSuccessCode.PROGRESS_FOUND;
        return ApiResponse.onSuccess(code,null);
    }

}
