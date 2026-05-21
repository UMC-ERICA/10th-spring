package umc.server.domain.review.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.dto.response.GetMyReviewsResponse;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.entity.enums.ReviewSortType;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.global.paging.CursorEncoder;
import umc.server.global.paging.CursorPageResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    private static final int SIZE = 10;

    public CursorPageResponse<GetMyReviewsResponse> getMyReviews(
            Long memberId,
            ReviewSortType sortType,
            String cursor
    ) {
        Member member = findMemberById(memberId);

        Long cursorId = null;
        Float cursorScore = null;

        if (cursor != null) {
            String decoded = CursorEncoder.decode(cursor);
            if (sortType == ReviewSortType.SCORE) {
                String[] parts = decoded.split(",");
                cursorScore = Float.parseFloat(parts[0]);
                cursorId = Long.parseLong(parts[1]);
            } else {
                cursorId = Long.parseLong(decoded);
            }
        }

        List<Review> fetched = switch (sortType) {
            case ID -> reviewRepository.findByMemberOrderById(
                    member, cursorId, PageRequest.of(0, SIZE + 1));
            case SCORE -> reviewRepository.findByMemberOrderByScore(
                    member, cursorScore, cursorId, PageRequest.of(0, SIZE + 1));
        };

        boolean hasNext = fetched.size() > SIZE;
        List<Review> page = hasNext ? fetched.subList(0, SIZE) : fetched;

        List<GetMyReviewsResponse> contents = page.stream()
                .map(GetMyReviewsResponse::from)
                .toList();

        Review last = hasNext ? page.get(page.size() - 1) : null;
        String nextCursor = null;
        if (last != null) {
            String raw = sortType == ReviewSortType.SCORE
                    ? last.getScore() + "," + last.getId()
                    : String.valueOf(last.getId());
            nextCursor = CursorEncoder.encode(raw);
        }

        return new CursorPageResponse<>(contents, nextCursor, hasNext);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
