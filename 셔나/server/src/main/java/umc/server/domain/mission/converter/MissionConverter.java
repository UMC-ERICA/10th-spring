package umc.server.domain.mission.converter;

import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.entity.Mission;

import java.util.List;

public class MissionConverter {

    // entity -> 미션 조회 DTO
    public static MissionResDTO.MissionDTO toMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(memberMission.getMission().getId())
                .missionTitle(memberMission.getMission().getMissionTitle())
                .missionDescription(memberMission.getMission().getMissionDescription())
                .rewardPoints(memberMission.getMission().getRewardPoints())
                .missionStatus(memberMission.getMissionStatus())
                .deadline(memberMission.getMission().getDeadline())
                .build();
    }

    // entity -> 미션 목록 조회 DTO
    public static MissionResDTO.GetMissionListDTO toGetMissionListDTO(List<MemberMission> memberMissionList) {
        List<MissionResDTO.MissionDTO> missionDTOList = memberMissionList.stream()
                .map(MissionConverter::toMissionDTO)
                .toList();

        return MissionResDTO.GetMissionListDTO.builder()
                .missionList(missionDTOList)
                .build();
    }

    // entity -> 미션 상태 변경 DTO
    public static MissionResDTO.UpdateMissionStatusResultDTO toUpdateMissionStatusResultDTO(MemberMission memberMission) {
        return MissionResDTO.UpdateMissionStatusResultDTO.builder()
                .missionId(memberMission.getMission().getId())
                .missionStatus(memberMission.getMissionStatus())
                .build();
    }

    // entity -> 홈 미션 조회 DTO
    public static MissionResDTO.HomeMissionDTO toHomeMissionDTO(Mission mission) {
        return MissionResDTO.HomeMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getStoreName())
                .category(mission.getStore().getCategory())
                .missionTitle(mission.getMissionTitle())
                .rewardPoints(mission.getRewardPoints())
                .deadline(mission.getDeadline())
                .build();
    }

    // entity -> 홈 미션 목록 조회 DTO
    public static MissionResDTO.GetHomeMissionListDTO toGetHomeMissionListDTO(List<Mission> missionList) {
        List<MissionResDTO.HomeMissionDTO> homeMissionDTOList = missionList.stream()
                .map(MissionConverter::toHomeMissionDTO)
                .toList();

        return MissionResDTO.GetHomeMissionListDTO.builder()
                .homeMissionList(homeMissionDTOList)
                .build();
    }
}
