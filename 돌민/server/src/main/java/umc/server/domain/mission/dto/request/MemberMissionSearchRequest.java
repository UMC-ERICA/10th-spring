package umc.server.domain.mission.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.server.domain.mission.enums.MemberMissionStatus;

@Getter
@Setter
@NoArgsConstructor
public class MemberMissionSearchRequest {

    private MemberMissionStatus status;
}
