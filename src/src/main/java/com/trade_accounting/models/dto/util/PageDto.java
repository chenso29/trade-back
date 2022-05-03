package src.main.java.com.trade_accounting.models.dto.util;

import java.util.List;

public class PageDto<T> {

    private int numberOfElements;
    private List<T> content;
    private long totalElements;
    private int totalPages;

    public PageDto() {
    }

    public PageDto(List<T> content, long totalElements, int totalPages, int numberOfElements) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }
}
