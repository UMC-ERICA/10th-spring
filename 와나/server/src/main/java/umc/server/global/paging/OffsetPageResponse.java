package umc.server.global.paging;

import java.util.List;
import org.springframework.data.domain.Page;

public record OffsetPageResponse<T>(
        List<T> contents,
        int page,
        int size,
        long totalElements
) {
    public static <T> OffsetPageResponse<T> from(Page<T> page) {
        return new OffsetPageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
}
