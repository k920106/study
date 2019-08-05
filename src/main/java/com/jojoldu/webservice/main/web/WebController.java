package com.jojoldu.webservice.main.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    // 홈
    @GetMapping("/")
    public String main() {
        return "main";
    }

}