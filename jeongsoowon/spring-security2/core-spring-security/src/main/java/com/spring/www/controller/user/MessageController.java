package com.spring.www.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
    @GetMapping("/messages")
    public String message() {
        return "user/messages";
    }

    @ResponseBody
    @PostMapping("/api/messages")
    //public String apiMessage() {
    public ResponseEntity apiMessage() {
        //return "messages ok";
        return ResponseEntity.ok().body("ok");
    }
}
