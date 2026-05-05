package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.review.dto.request.CreateReviewRequest;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public void createReview(Long memberId, Long missionId, CreateReviewRequest request) {
        // TODO : 추후 구현
    }
}
