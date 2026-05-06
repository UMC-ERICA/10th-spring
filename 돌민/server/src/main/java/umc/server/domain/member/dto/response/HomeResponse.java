package umc.server.domain.member.dto.response;

import umc.server.global.apiPayload.PageResponse;

public record HomeResponse(
        Long memberId,
        String selectedDong,
        Integer point,
        boolean hasNewAlarm,
        long completedMissionCount,
        PageResponse<MissionHomeResponse> missions
) {
}
