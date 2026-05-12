package umc.server.domain.mission.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewResDTO;

@Builder
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

    public static MissionResDTO.MissionsGetResDTO toMissionsGetResDTO(List<Mission> missions) {
        List<MissionResDTO.MissionsResDTO> MissionResDTOList = missions.stream()
                .map(MissionConverter::toMissionsResDTO) // 위에서 만든 단일 변환 메서드 활용
                .collect(Collectors.toList());

        return MissionResDTO.MissionsGetResDTO.builder()
                .missions(MissionResDTOList) // 변환된 DTO 리스트 설정
                .build();
    }
}
