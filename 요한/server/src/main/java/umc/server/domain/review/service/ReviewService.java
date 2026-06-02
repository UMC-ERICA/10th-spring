package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewRequestDTO;
import umc.server.domain.review.dto.ReviewResponseDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.enums.ReviewSortType;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Review createReview(Long memberId, Long memberMissionId, ReviewRequestDTO.CreateReviewDTO request) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_MISSION_NOT_FOUND));

        // 미션 소유자 확인
        if (!memberMission.getMember().getId().equals(memberId)) {
            throw new GeneralException(GeneralErrorCode.FORBIDDEN);
        }

        // 미션 상태가 COMPLETE인지 확인
        if (!memberMission.getStatus().equals(MissionStatus.COMPLETE)) {
            throw new GeneralException(GeneralErrorCode.MEMBER_MISSION_NOT_COMPLETE);
        }

        Review newReview = ReviewConverter.toReview(request, memberMission);
        return reviewRepository.save(newReview);
    }

    public ReviewResponseDTO.ReviewListDTO getReviewList(Long memberId, ReviewRequestDTO.ReviewListRequestDTO request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, request.pageSize());
        Slice<Review> reviewSlice = fetchReviewSlice(member, request, pageRequest);

        String nextCursor = null;
        if (reviewSlice.hasNext()) {
            Review last = reviewSlice.getContent().get(reviewSlice.getNumberOfElements() - 1);
            nextCursor = request.sortType() == ReviewSortType.ID ?
                    "ID:" + last.getId() : "RATING:" + last.getRating() + ":ID:" + last.getId();
        }

        return ReviewConverter.toReviewListDTO(reviewSlice, nextCursor, request.pageSize());
    }

    private Slice<Review> fetchReviewSlice(Member member, ReviewRequestDTO.ReviewListRequestDTO request, PageRequest pageRequest) {
        if (request.sortType() == ReviewSortType.ID) {
            if (request.cursor() == null) return reviewRepository.findAllByMemberOrderByIdDesc(member, pageRequest);
            return reviewRepository.findAllByMemberAndIdLessThanOrderByIdDesc(member, Long.parseLong(request.cursor().split(":")[1]), pageRequest);
        }

        if (request.sortType() == ReviewSortType.RATING) {
            if (request.cursor() == null)
                return reviewRepository.findAllByMemberOrderByRatingDescIdDesc(member, pageRequest);
            String[] parts = request.cursor().split(":");
            return reviewRepository.findAllByMemberAndRatingAndIdLessThanOrderByRatingDescIdDesc(member, Integer.parseInt(parts[1]), Long.parseLong(parts[3]), pageRequest);
        }

        throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
    }
}
