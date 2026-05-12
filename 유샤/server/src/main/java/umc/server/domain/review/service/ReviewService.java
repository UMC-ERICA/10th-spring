package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
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
            Integer pageNumber,
            Integer pageSize,
            String sort
    ) {
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }else{
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Review> reviewPage = reviewRepository.findReviews(memberId,pageRequest);
        return ReviewConverter.toPagination(
                reviewPage.map(ReviewConverter::toGetReview).toList(),
                reviewPage.getNumber(),
                reviewPage.getSize()
        );

    }
}
