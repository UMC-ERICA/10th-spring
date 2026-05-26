package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.review.dto.ReviewReqDTO;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public void createReview(Long storeId, ReviewReqDTO.CreateReview request) {

    }
}