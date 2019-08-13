package com.jojoldu.webservice.board.web;

import com.jojoldu.webservice.board.dto.PostsMainResponseDto;
import com.jojoldu.webservice.board.service.BoardService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    // 게시판 - 메인
    @GetMapping("/board-main")
    public String board(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("userid") == null) {

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('로그인이 필요합니다!');</script>");

            out.flush();

            return "login/login-main";

        } else {

            String userid = (String) session.getAttribute("userid");

            List<PostsMainResponseDto> postall = boardService.findAllDesc();

            System.out.println(postall);

            model.addAttribute("posts", postall);

            return "/board/board-main";

        }

    }

}