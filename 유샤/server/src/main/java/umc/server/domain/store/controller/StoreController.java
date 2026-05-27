package umc.server.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.exception.code.MissionSuccessCode;
import umc.server.domain.mission.service.MissionService;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final ReviewService reviewService;
    private final MissionService missionService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.PostReview>  postReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.PostReview dto
            ){

        ReviewResDTO.PostReview result = reviewService.createReview(storeId, dto);
        BaseSuccessCode code = ReviewSuccessCode.CREATED;

        return ApiResponse.onSuccess(code,result);
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
            ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code,missionService.create(storeId,dto));
    }

    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissionList(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.getMissionListByStore(storeId,pageSize,pageNumber,sort));
    }
}
