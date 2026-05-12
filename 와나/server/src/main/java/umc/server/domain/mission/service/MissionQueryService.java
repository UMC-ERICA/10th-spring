package umc.server.domain.mission.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberMission;
import umc.server.domain.member.entity.enums.MemberMissionStatus;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberMissionRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.dto.GetMissionsCountResponse;
import umc.server.domain.mission.dto.GetMissionsResponse;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.enums.MissionStatus;
import umc.server.domain.mission.exception.MissionErrorCode;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.restaurant.entity.Restaurant;
import umc.server.domain.restaurant.entity.RestaurantCategory;
import umc.server.domain.restaurant.entity.enums.FoodCategory;
import umc.server.domain.restaurant.repository.RestaurantCategoryRepository;
import umc.server.global.paging.CursorPageResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final RestaurantCategoryRepository restaurantCategoryRepository;

    private static final int SIZE = 10;

    public CursorPageResponse<GetMissionsResponse> getMyMissions(
            Long memberId,
            MemberMissionStatus status,
            Long cursor
    ) {
        Member member = findMemberById(memberId);

        List<MemberMission> fetched = memberMissionRepository.findByMemberAndStatusWithCursor(
                member, status, cursor, PageRequest.of(0, SIZE + 1)
        );

        boolean hasNext = fetched.size() > SIZE;
        List<MemberMission> page = hasNext ? fetched.subList(0, SIZE) : fetched;

        List<Mission> missions = page.stream()
                .map(MemberMission::getMission)
                .collect(Collectors.toList());

        Map<Long, FoodCategory> categoryMap = buildCategoryMap(
                missions.stream().map(Mission::getRestaurant).collect(Collectors.toList())
        );

        List<GetMissionsResponse> contents = missions.stream()
                .map(m -> GetMissionsResponse.from(m,
                        categoryMap.getOrDefault(m.getRestaurant().getId(), FoodCategory.OTHER)))
                .collect(Collectors.toList());

        Long nextCursor = hasNext ? page.get(page.size() - 1).getId() : null;
        return new CursorPageResponse<>(contents, nextCursor, hasNext);
    }

    public List<GetMissionsResponse> getMissions(MissionStatus missionStatus) {
        List<Mission> missions = missionRepository.findAllByStatus(missionStatus);

        Map<Long, FoodCategory> categoryMap = buildCategoryMap(
                missions.stream().map(Mission::getRestaurant).collect(Collectors.toList())
        );

        return missions.stream()
                .map(m -> GetMissionsResponse.from(m,
                        categoryMap.getOrDefault(m.getRestaurant().getId(), FoodCategory.OTHER)))
                .collect(Collectors.toList());
    }

    public GetMissionsCountResponse getMyMissionCount(Long memberId, MemberMissionStatus status) {
        Member member = findMemberById(memberId);
        int totalCount = memberMissionRepository.countByMember(member);
        int completedCount = memberMissionRepository.countByMemberAndStatus(member, MemberMissionStatus.COMPLETED);
        return new GetMissionsCountResponse(totalCount, completedCount);
    }

    private Map<Long, FoodCategory> buildCategoryMap(List<Restaurant> restaurants) {
        if (restaurants.isEmpty()) {
            return Map.of();
        }
        return restaurantCategoryRepository.findByRestaurantIn(restaurants).stream()
                .collect(Collectors.toMap(
                        rc -> rc.getRestaurant().getId(),
                        RestaurantCategory::getCategory,
                        (first, second) -> first
                ));
    }

    private Mission findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
