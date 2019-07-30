package com.jojoldu.webservice.web;

import com.jojoldu.webservice.dto.posts.PostsMainResponseDto;
import com.jojoldu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")
    public String main(Model model) {

        List<PostsMainResponseDto> postall = postsService.findAllDesc();

        model.addAttribute("posts", postall);

        return "main";
    }

}