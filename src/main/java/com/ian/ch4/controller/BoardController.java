package com.ian.ch4.controller;

import com.ian.ch4.domain.BoardDto;
import com.ian.ch4.domain.PageHandler;
import com.ian.ch4.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시글 조회
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
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "boardList";
    }

    // 게시글 등록 화면 조회
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("mode", "new");

        return "board";
    }

    // 게시글 등록 처리
    @PostMapping("/create")
    public String create(BoardDto boardDto, HttpServletRequest request) {
        boardDto.setWriter(
                (String) request.getSession().getAttribute("username")
        );

        BoardDto result = boardService.save(boardDto);

        return "redirect:/boards/" + result.getBoardId();
    }

    // 단일 게시글 조회
    @GetMapping("/{boardId}")
    public String findById(
            @PathVariable Long boardId, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize, Model model
    ) {
        BoardDto board = boardService.findById(boardId);

        model.addAttribute("board", board);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "board";
    }

    // 게시글 수정 화면 조회
    @GetMapping("/{boardId}/update")
    public String updateForm(
            @PathVariable Long boardId, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize, Model model
    ) {
        BoardDto board = boardService.findById(boardId);

        model.addAttribute("board", board);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "board";
    }

    // 게시글 수정 처리
    @PostMapping("/{boardId}/update")
    public String update(BoardDto boardDto, Model model) {
        BoardDto result = boardService.update(boardDto);

        model.addAttribute("board", result);

        return "redirect:/boards/" + result.getBoardId();
    }

    // 게시글 삭제
    @PostMapping("/{boardId}/delete")
    public String delete(
            @PathVariable Long boardId, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        boardService.delete(boardId);

        return "redirect:/boards?page=" + page + "&pageSize=" + pageSize;
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return session.getAttribute("username") != null;
    }
}
