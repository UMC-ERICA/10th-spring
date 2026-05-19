package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.exception.ReviewException;
import umc.server.domain.review.exception.code.ReviewErrorCode;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResDTO.PostReview createReview(
            Long storeId,
            ReviewReqDTO.PostReview dto
    ){
        Store store = storeRepository.findById(storeId).orElseThrow();
        Member member = memberRepository.findById(1L).orElseThrow();

        Review review = Review.builder()
                        .store(store)
                        .member(member)
                        .content(dto.content())
                        .star(dto.star())
                        .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toPostReviewResult(savedReview);
    }

    public ReviewResDTO.Pagination<ReviewResDTO.GetReview> findReviewsPage(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {

        PageRequest pageRequest = PageRequest.of(0,pageSize);

        long idCursor;
        Slice<Review> reviews;
        String nextCursor;

        if(!cursor.equals("-1")){

            String[] cursorSplit = cursor.split(":");
            switch(query.toLowerCase()){
                case "id":
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    reviews = reviewRepository.findReviewsByMember_IdAndIdLessThanOrderByIdDesc(
                            memberId,
                            idCursor,
                            pageRequest
                    );
                    break;
                case "star":
                    Integer starCursor = Integer.parseInt(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    reviews = reviewRepository.findReviewsByMember_IdAndStarCursor(
                            memberId,
                            starCursor,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }else{
            reviews = reviewRepository.findReviewsByMember_IdOrderByIdDesc(memberId, pageRequest);
        }

        nextCursor = reviews.getContent().getLast().getId() + ":" + reviews.getContent().getLast().getId();

        return  ReviewConverter.toPagination(
                reviews.map(ReviewConverter::toGetReview).toList(),
                reviews.hasNext(),
                nextCursor,
                pageSize
        );
    }
}
