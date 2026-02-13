package com.ian.ch4.controller;

import com.ian.ch4.dao.UserDao;
import com.ian.ch4.domain.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDao userDao;

    // 회원가입 화면 조회
    @GetMapping("/register")
    public String registerForm() {
        return "registerForm";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(UserDto userDto) {
        // 1. 유효성 검사

        // 2. 회원 등록
        userDao.save(userDto);

        return "redirect:/auth/login";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 종료

        return "redirect:/";
    }

    // 로그인 화면 조회
    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username, String password, String referer, boolean rememberId,
            HttpServletRequest request, HttpServletResponse response
    ) {
        // 1. 유효성 검사 -> 아이디 및 비밀번호가 다를 경우 로그인 화면으로 리다이렉트
        if (!loginCheck(username, password)) {
            return "redirect:/auth/login";
        }

        // 2. 요청에서 세션 획득 및 세션에 아이디 저장
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        // 로그인 기억 활성화 시 쿠키 생성 및 응답에 반환, 비활성화 시 쿠키 삭제
        Cookie cookie = new Cookie("username", username);
        if (!rememberId) cookie.setMaxAge(0); // 쿠키 삭제 -> 기존에 등록된 쿠키가 있을 수 있음
        response.addCookie(cookie);

        // 요청에서 넘어온 referer 값이 존재하지 않는 경우 홈 화면, 존재하는 경우 해당 url로 리다이렉트
        referer = !StringUtils.hasText(referer) ? "/" : referer;
        return "redirect:" + referer;
    }

    private boolean loginCheck(String username, String password) {
        UserDto userDto = null;

        try {
            userDto = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return userDto != null && userDto.getPassword().equals(password);
    }
}
