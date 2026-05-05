package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.exception.MissionErrorCode;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.restaurant.entity.Restaurant;
import umc.server.domain.restaurant.repository.RestaurantRepository;
import umc.server.domain.review.dto.request.CreateReviewRequest;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    public void createReview(Long memberId, Long missionId, CreateReviewRequest request) {
        Member member = findMemberById(memberId);
        Mission mission = findMissionById(missionId);
        Restaurant restaurant = mission.getRestaurant();

        // 리뷰생성
        Review review = Review.create(
                member,
                restaurant,
                request.content(),
                request.score()
        );

        reviewRepository.save(review);
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
