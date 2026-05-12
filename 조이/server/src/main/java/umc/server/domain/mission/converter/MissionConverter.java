package umc.server.domain.mission.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.store.entity.Store;

public class MissionConverter {
    //미션가져오기
    public static MissionResDTO.MissionsResDTO toMissionsResDTO(Mission mission) {
        return MissionResDTO.MissionsResDTO.builder()
                .missionId(mission.getId())
                .missionInfo(mission.getMissionInfo())
                .point(mission.getPoint())
                .status(mission.getStatus())
                .build();
    }

    //리스트미션
    public static MissionResDTO.MissionsGetResDTO toMissionsGetResDTO(List<Mission> missions) {
        List<MissionResDTO.MissionsResDTO> MissionResDTOList = missions.stream()
                .map(MissionConverter::toMissionsResDTO) // 위에서 만든 단일 변환 메서드 활용
                .collect(Collectors.toList());

        return MissionResDTO.MissionsGetResDTO.builder()
                .missions(MissionResDTOList) // 변환된 DTO 리스트 설정
                .build();
    }

    // 가게 미션 생성
    public static Mission toMission(Store store, Long storeId, MissionReqDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .id(storeId)
                .status(dto.status())
                .point(dto.point())
                .missionInfo(dto.missionInfo())
                .deadline(dto.deadline())
                .build();
    }

    //페이지네이션 툴 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer PageNumber,
            Integer PageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(PageNumber)
                .pageSize(PageSize)
                .build();
    }
}
