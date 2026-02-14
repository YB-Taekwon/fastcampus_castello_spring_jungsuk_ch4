package com.ian.ch4.controller;

import com.ian.ch4.domain.BoardDto;
import com.ian.ch4.domain.PageHandler;
import com.ian.ch4.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String getBoardList(
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
            Model model, HttpServletRequest request
    ) {
        // 1. 로그인 여부 확인
        if (!loginCheck(request))
            return "redirect:/auth/login?referer=" + request.getRequestURL();

        int totalCount = boardService.count();
        PageHandler pageHandler = new PageHandler(totalCount, page, pageSize);

        Map<String, Integer> params = new HashMap<>();
        params.put("offset", (page - 1) * pageSize);
        params.put("pageSize", pageSize);

        List<BoardDto> boardList = boardService.getPage(params);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageHandler", pageHandler);

        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return session.getAttribute("username") != null;
    }
}
