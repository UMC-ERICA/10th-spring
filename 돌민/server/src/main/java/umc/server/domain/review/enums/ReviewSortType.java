package umc.server.domain.review.enums;

/**
 * 리뷰 정렬 기준
 *
 * ID   → 최신순 (ID가 클수록 최근에 작성된 리뷰)
 * STAR → 별점 높은 순 (같은 별점이면 최신순으로 정렬)
 */
public enum ReviewSortType {
    ID, STAR
}
