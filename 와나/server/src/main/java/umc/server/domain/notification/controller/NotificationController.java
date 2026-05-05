package umc.server.domain.notification.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.notification.dto.NotificationReadStatusResponse;
import umc.server.domain.notification.service.NotificationQueryService;
import umc.server.global.exception.apiPayload.ApiResponse;
import umc.server.global.exception.code.CommonSuccessCode;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationQueryService notificationQueryService;

    @GetMapping("/notifications/count")
    public ApiResponse<NotificationReadStatusResponse> existUnreadNotifications() {
        // 추후 Security를 통해 자신의 memberId를 가져옴. 지금은 임시로 1L 사용
        Long memberId = 1L;
        return ApiResponse.success(CommonSuccessCode.OK, notificationQueryService.existUnreadNotifications(memberId));
    }
}
