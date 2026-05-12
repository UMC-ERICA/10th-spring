package umc.server.domain.mission.exception;

import umc.server.global.exception.GlobalException;
import umc.server.global.exception.code.BaseCode;

public class MissionException extends GlobalException {
    public MissionException(BaseCode baseCode) {
        super(baseCode);
    }
}
