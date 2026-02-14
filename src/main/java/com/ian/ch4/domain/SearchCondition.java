package com.ian.ch4.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCondition {

    // 검색
    private String keyword = "";
    private String option = "";

    // 페이징
    private Integer offset = 0;
    private Integer page = 1;
    private Integer pageSize = 10;

    public SearchCondition(String keyword, String option, Integer page, Integer pageSize) {
        this.keyword = keyword;
        this.option = option;
        this.page = page;
        this.pageSize = pageSize;
    }
}
