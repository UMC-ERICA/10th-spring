package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResDTO.CreateReviewResultDTO createReview(ReviewReqDTO.CreateReviewDTO request) {

        // 존재하는 회원인지 확인
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new StoreException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 존재하는 가게인지 확인
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 리뷰 엔티티 생성 (DTO -> Entity 변환)
        Review review = ReviewConverter.toReview(request);
        review.setMember(member);
        review.setStore(store);

        // 리뷰 저장
        Review savedReview = reviewRepository.save(review);

        // 결과 반환 (Entity -> DTO 변환)
        return ReviewConverter.toCreateReviewResultDTO(savedReview);
    }

    public ReviewResDTO.GetReviewListDTO<ReviewResDTO.ReviewDTO> getReviewList(Long storeId, Long cursorId, BigDecimal cursorRating, String sortBy, Integer size) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 다음 페이지 존재 여부 확인
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Review> reviews;

        // 조회 조건
        if ("RATING".equalsIgnoreCase(sortBy)) {
            reviews = reviewRepository.findMyReviewsByRatingDesc(storeId, cursorRating, cursorId, pageRequest);
        } else {
            reviews = reviewRepository.findMyReviewsByIdDesc(storeId, cursorId, pageRequest);
        }

        // 다음 페이지 존재 여부 판단
        boolean hasNext = reviews.size() > size;
        if (hasNext) {
            reviews.remove(size.intValue());  // 마지막에 가져온 '확인용' 데이터 1개는 제거
        }

        // 다음 커서 정보 추출 (데이터가 있을 경우 마지막 요소 기준)
        Long nextCursorId = reviews.isEmpty() ? null : reviews.get(reviews.size() - 1).getId();
        BigDecimal nextCursorRating = reviews.isEmpty() ? null : reviews.get(reviews.size() - 1).getRating();

        List<ReviewResDTO.ReviewDTO> reviewDTOList = reviews.stream()
                .map(ReviewConverter::toReviewDTO)
                .toList();

        return ReviewConverter.toGetReviewResultDTO(reviewDTOList, nextCursorId, nextCursorRating, hasNext);
    }
}
