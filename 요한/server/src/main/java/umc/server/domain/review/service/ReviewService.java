package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewRequestDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public Review createReview(Long memberMissionId, ReviewRequestDTO.CreateReviewDTO request) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_MISSION_NOT_FOUND));

        // 미션 상태가 COMPLETE인지 확인
        if (!memberMission.getStatus().equals(MissionStatus.COMPLETE)) {
            throw new GeneralException(GeneralErrorCode.MEMBER_MISSION_NOT_COMPLETE);
        }

        Review newReview = ReviewConverter.toReview(request, memberMission);
        return reviewRepository.save(newReview);
    }
}
