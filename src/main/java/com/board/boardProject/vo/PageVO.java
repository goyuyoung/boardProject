package com.board.boardProject.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageVO<T> {

    private List<T> list = new ArrayList<>();

    private Page page = new Page();

    @Data
    public class Page {
        int currentPage;
        int startPage;
        int endPage;
        boolean prev;
        boolean next;
    }
}
