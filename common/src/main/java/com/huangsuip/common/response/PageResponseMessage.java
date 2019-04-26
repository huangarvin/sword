package com.huangsuip.common.response;

import java.util.List;

/**
 * @author HuangSuip
 */
public class PageResponseMessage<E> extends ResponseMessage<Iterable<E>> {


    private final Integer page;
    private final Integer pageSize;
    private final Integer total;
    private final Integer totalPages;


    protected PageResponseMessage(Integer page, Integer pageSize, Integer total, List<E> data) {
        super(CODE_OK, null, data);
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        total = total == null ? 0 : total;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPages = (int) Math.ceil((double) total / (double) pageSize);
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
