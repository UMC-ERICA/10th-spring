package umc.server.domain.review.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.exception.ReviewException;
import umc.server.domain.review.exception.code.ReviewErrorCode;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    //리뷰조회 페이지네이션 ver
    public ReviewResDTO.Pagination<ReviewResDTO.ReviewGetDTO> getReviewList(Long memberId, Integer pageSize,
                                                                            String cursor,
                                                                            String query) {

        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewList;

        if ((cursor != null && !cursor.equals("-1"))) {
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":
                    long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findReviewByMemberId_AndIdLessThan(memberId, idCursor, pageRequest);
                    break;
                case "star":
                    reviewList = reviewRepository.findByMemberIdOrderByStarDesc(memberId, pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_FOUND);
            }
        } else {
            reviewList = reviewRepository.findReviewByMemberId_IdOrderByIdDesc(memberId, pageRequest);
        }

        String nextCursor = reviewList.getContent().getLast().getId() + ":" + reviewList.getContent().getLast().getId();

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toReviewGetDTO).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }

}
