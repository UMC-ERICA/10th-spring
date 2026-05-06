package umc.server.domain.review.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.ReviewPreViewListDTO getReviewList(Long storeId) {

        // 있는 가게인지 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        List<Review> reviews = reviewRepository.findByStoreId(storeId);

        return ReviewConverter.toReviewPreViewListDTO(reviews);

    }

}
