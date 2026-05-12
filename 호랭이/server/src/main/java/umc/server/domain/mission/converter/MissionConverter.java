package umc.server.domain.mission.converter;

import jakarta.persistence.criteria.CriteriaBuilder;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.store.entitty.Store;

import java.util.List;

public class MissionConverter {
    //가게 미션 생성
    public static Mission toMission( // 클라이언트가 보낸 DTO, db에 저장할 엔티로 변환
        Store store, // 미션이 등록될 상점 엔티티 객체
       MissionReqDTO.CreateMission dto //생성된 미션 세부정보
    ) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    //가게 내 미션조회
    public static MissionResDTO.GetMission toGetMission(Mission mission) { //toGetMision안에다 가두기
        return MissionResDTO.GetMission.builder() //DB의Mission엔티티를 DTO로 변환
                .missionId(mission.getId())
                .point(mission.getPoint())
                .condition(mission.getConditional())
                .build();
    }



    //나의 미션 조회
    public static MissionResDTO.GetMyMission toGetMyMission(MemberMission memberMission) {
        return MissionResDTO.GetMyMission.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionContent(memberMission.getMission().getConditional())
                .reward(memberMission.getMission().getPoint())
                .status(memberMission.getStatus().toString()) //enum타입이라 toString
                .build();

    }


    //페이지네이션 틀 -> 필요한 정보만 반환
    public static <T>MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
