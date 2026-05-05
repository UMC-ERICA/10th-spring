package umc.server.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.mission.dto.MissionResponseDTO;
import umc.server.domain.mission.entity.mapping.MemberMission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission memberMission) {
        return new MissionResponseDTO.MissionPreviewDTO(
                memberMission.getMission().getId(),
                memberMission.getMission().getStore().getName(),
                memberMission.getMission().getContent(),
                memberMission.getMission().getRewardValue(),
                memberMission.getStatus(),
                memberMission.getCreatedAt()
        );
    }

    public static MissionResponseDTO.MissionListDTO toMissionListDTO(Page<MemberMission> memberMissionPage) {
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = memberMissionPage.getContent().stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return new MissionResponseDTO.MissionListDTO(
                missionPreviewDTOList,
                missionPreviewDTOList.size(),
                memberMissionPage.getTotalPages(),
                memberMissionPage.getTotalElements(),
                memberMissionPage.isFirst(),
                memberMissionPage.isLast()
        );
    }
}
