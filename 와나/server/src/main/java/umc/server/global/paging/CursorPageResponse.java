package umc.server.global.paging;

import java.util.List;

public record CursorPageResponse<T>(
        List<T> contents,
        String nextCursor,
        boolean hasNext
) {
}
