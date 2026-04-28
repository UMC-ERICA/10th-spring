package umc.server.domain.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.notification.dto.response.NewAlarmResponse;
import umc.server.global.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/{memberId}/message")
public class AlarmController {

    @GetMapping("/new")
    public ApiResponse<NewAlarmResponse> hasNewAlarm(
            @PathVariable Long memberId
    ) {
        // TODO: alarmService.hasNewAlarm(memberId);

        NewAlarmResponse result = new NewAlarmResponse(
                true,
                3L
        );

        return ApiResponse.onSuccess(result);
    }
}