package umc.server.domain.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.collection.internal.StandardOrderedMapSemantics;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.exception.code.MissionErrorCode;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.exception.Code.ReviewErrorCode;
import umc.server.domain.review.exception.ReviewException;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entitty.Store;
import umc.server.domain.store.exception.Code.StoreErrorCode;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void createReview(Long storeId, ReviewReqDTO.CreateReview request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //리뷰 생성
        Review review = ReviewConverter.toReview(store, request);
        reviewRepository.save(review);
    }

    //리뷰 조회
    public ReviewResDTO.CursorPage<ReviewResDTO.ReviewInfo> getReview(
            Long storeId,
            Integer pageSize,
            String cursor,
            String sort
    ){

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        //커서가 있는 경우
        if (!cursor.equals("-1")){

            //커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (sort.toLowerCase()){
                case"id":
                    //커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    reviewList = reviewRepository.findReviewsByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
            reviewList = reviewRepository.findReviewsByStore_IdOrderByIdDesc(storeId,pageRequest);
        }

        //다음 커서 제안
        nextCursor = reviewList.getContent().getLast().getId() + ":" + reviewList.getContent().getLast().getId();

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );

}