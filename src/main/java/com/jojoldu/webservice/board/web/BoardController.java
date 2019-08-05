package com.jojoldu.webservice.board.web;

import com.jojoldu.webservice.board.dto.PostsMainResponseDto;
import com.jojoldu.webservice.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    // 게시판 - 메인
    @GetMapping("/board-main")
    public String board(Model model) {

        List<PostsMainResponseDto> postall = boardService.findAllDesc();

        model.addAttribute("posts", postall);

        return "/board/board-main";
    }

}
