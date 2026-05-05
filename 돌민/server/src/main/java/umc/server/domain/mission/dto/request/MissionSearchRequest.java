package umc.server.domain.mission.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.server.domain.mission.enums.MissionStatus;

@Getter
@Setter
@NoArgsConstructor
public class MissionSearchRequest {

    private MissionStatus status;
    private Integer page = 0;
    private Integer size = 10;
}
