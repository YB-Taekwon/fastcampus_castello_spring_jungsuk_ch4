package com.ian.ch4.domain;

import lombok.Data;

@Data
public class PageHandler {

    private int totalCount; // 전체 게시글 수
    private int pageSize; // 한 페이지에 표시되는 게시글 수
    private int navSize = 10; // 페이지 내비게이션의 크기
    private int totalPage; // 전체 페이지 수
    private int page; // 현재 페이지
    private int startPage; // 첫 번째 페이지
    private int endPage; // 마지막 페이지
    private boolean showPrev; // 이전 페이지로 넘어가는 버튼을 보여줄지 확인 -> 첫 번째 페이지에서는 false
    private boolean showNext; // 다음 페이지로 넘어가는 버튼을 보여줄지 확인 -> 마지막 페이지에서는 false

    public PageHandler(int totalCount, int page) {
        this(totalCount, page, 10);
    }

    public PageHandler(int totalCount, int page, int pageSize) {
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;

        totalPage = (totalCount + pageSize - 1) / pageSize;
        startPage = ((page - 1) / navSize) * navSize + 1;
        endPage = Math.min(startPage + navSize - 1, totalPage);
        ;
        showPrev = startPage != 1;
        showNext = endPage != totalPage;
    }

    void print() {
        System.out.println("page = " + page);
        System.out.print(showPrev ? "PREV " : "");

        for (int i = startPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }
        System.out.println(showNext ? " NEXT" : "");
    }
}