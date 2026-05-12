package umc.server.domain.mission.dto;

import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    //가게 내 미션조회
    @Builder
    public record GetMission(
            Long missionId, //클라이언트에 응답데이터 만드려고
            Integer point,
            String condition
    ){}

    // 페이지네이션
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

    //커서페이징
    @Builder
    public record CursorPage<T>(
            List<T> data,
            String nextCursor,
            Boolean hasNext
    ){}

    @Builder // 홈 화면 전체응답
    public record HomeView(
            String area,
            Integer point,
            Integer totalMission,
            Integer completedMission,
            List<MissionDetail> myMissions
    ) {}

    @Builder //미션 1개 상세정보
    public record MissionDetail(
            String storeName,
            String category,
            String content,
            Integer dDay
    ) {}

    @Builder // 미션 여러개 리스트로 묶은 응답
    public record MissionStatusList(
            List<MissionDetail> missions
    ) {}

    //사용자 미션 조회
    @Builder
    public record GetMyMission(
            Long missionId,
            String storeName,
            String missionContent,
            Integer reward,
            String status
    ) {}
}