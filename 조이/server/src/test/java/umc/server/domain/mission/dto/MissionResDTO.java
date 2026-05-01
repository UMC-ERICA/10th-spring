package umc.server.domain.mission.dto;

import java.util.List;
import umc.server.domain.mission.enums.Status;

public class MissionResDTO {

    public record MissionsResDTO(
            Long missionId,
            String MissionInfo,
            Integer point,
            Long storeId,
            Status status
    ) {
    }
    
    public record MissionsGetResDTO(
            List<MissionsResDTO> missions
    ) {
    }

}
