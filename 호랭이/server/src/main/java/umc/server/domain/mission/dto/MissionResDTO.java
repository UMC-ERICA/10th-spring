package umc.server.domain.mission.dto;

import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record HomeView(
            String area,
            Integer point,
            Integer totalMission,
            Integer completedMission,
            List<MissionDetail> myMissions
    ) {}

    @Builder
    public record MissionDetail(
            String storeName,
            String category,
            String content,
            Integer dDay
    ) {}

    @Builder
    public record MissionStatusList(
            List<MissionDetail> missions
    ) {}
}