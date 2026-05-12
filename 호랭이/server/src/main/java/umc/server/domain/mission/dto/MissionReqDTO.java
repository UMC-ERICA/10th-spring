package umc.server.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionReqDTO {


    //미션 생성
    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional
    ){}


    @Builder
    public record GetMyMission(
            Long userId
    ){}

}
