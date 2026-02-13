package com.ian.ch4.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PageHandlerTest {

    @Test
    void test1() {
        PageHandler pageHandler = new PageHandler(250, 1);
        pageHandler.print();

        System.out.println("pageHandler = " + pageHandler);
        assertThat(pageHandler.getStartPage()).isEqualTo(1);
        assertThat(pageHandler.getEndPage()).isEqualTo(10);
    }

    @Test
    void test2() {
        PageHandler pageHandler = new PageHandler(250, 11);
        pageHandler.print();

        System.out.println("pageHandler = " + pageHandler);
        assertThat(pageHandler.getStartPage()).isEqualTo(11);
        assertThat(pageHandler.getEndPage()).isEqualTo(20);
    }

    @Test
    void test3() {
        PageHandler pageHandler = new PageHandler(255, 25);
        pageHandler.print();

        System.out.println("pageHandler = " + pageHandler);
        assertThat(pageHandler.getStartPage()).isEqualTo(21);
        assertThat(pageHandler.getEndPage()).isEqualTo(26);
    }

}