package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionErrorCode;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.review.dto.request.MyReviewRequest;
import umc.server.domain.review.dto.request.ReviewCreateRequest;
import umc.server.domain.review.dto.response.ReviewResponse;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.ReviewSortType;
import umc.server.domain.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createReview(Long missionId, Long memberId, ReviewCreateRequest request) {
        Mission mission = missionRepository.findActiveById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));
        Member member = memberRepository.findActiveById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Review review = Review.builder()
                .star(request.star())
                .content(request.content())
                .store(mission.getStore())
                .member(member)
                .build();

        reviewRepository.save(review);
    }

    /**
     * 내가 작성한 리뷰 목록을 커서 기반 페이지네이션으로 조회
     */
    @Transactional(readOnly = true)
    public ReviewResponse.CursorResult getMyReviews(MyReviewRequest request) {
        memberRepository.findActiveById(request.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // size+1개를 조회해서 다음 페이지 존재 여부를 확인합니다.
        int fetchSize = request.size() + 1;
        PageRequest pageable = PageRequest.of(0, fetchSize);

        List<ReviewResponse.MyReview> reviews;

        if (request.sortBy() == ReviewSortType.ID) {
            // ID 정렬: cursorId 하나만 사용
            reviews = reviewRepository.findByMemberIdOrderByIdDesc(
                    request.memberId(),
                    request.cursorId(),  // 첫 페이지면 null
                    pageable
            );
        } else {
            // STAR 정렬: 복합 커서 (cursorStar + cursorId) 사용
            reviews = reviewRepository.findByMemberIdOrderByStarDesc(
                    request.memberId(),
                    request.cursorStar(), // 첫 페이지면 null
                    request.cursorId(),   // 첫 페이지면 null
                    pageable
            );
        }

        // 다음 페이지 존재 여부 확인
        boolean hasNext = reviews.size() > request.size();
        if (hasNext) {
            // size+1번째 항목은 "다음 페이지가 있다"는 신호용이므로 제거합니다.
            reviews = reviews.subList(0, request.size());
        }

        // 다음 페이지 커서 값 계산 (현재 페이지의 마지막 항목 기준)
        Long nextCursorId = null;
        Double nextCursorStar = null;

        if (hasNext && !reviews.isEmpty()) {
            ReviewResponse.MyReview lastReview = reviews.get(reviews.size() - 1);
            nextCursorId = lastReview.reviewId();
            if (request.sortBy() == ReviewSortType.STAR) {
                nextCursorStar = lastReview.star();
            }
        }

        return new ReviewResponse.CursorResult(reviews, hasNext, nextCursorId, nextCursorStar);
    }
}
