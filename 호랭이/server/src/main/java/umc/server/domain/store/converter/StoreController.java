package umc.server.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.store.dto.StoreReqDTO;
import umc.server.domain.store.dto.StoreResDTO;
import umc.server.domain.store.service.StoreService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/")
    public ApiResponse<StoreResDTO.StoreInfo> createStore(
            @RequestBody StoreReqDTO.JoinStore dto
    ) {
        return ApiResponse.onSuccess(storeService.createStore(dto));
    }

    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.StoreInfo> getStore(
            @PathVariable Long storeId
    ) {
        return ApiResponse.onSuccess(storeService.getStore(storeId));
    }
}