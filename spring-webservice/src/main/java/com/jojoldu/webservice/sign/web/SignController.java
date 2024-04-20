package com.jojoldu.webservice.sign.web;

import com.jojoldu.webservice.login.service.LoginService;
import com.jojoldu.webservice.sign.dto.SignDto;
import com.jojoldu.webservice.sign.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@AllArgsConstructor
public class SignController {

    private SignService signService;

    private LoginService loginService;

    // 회원가입 - 메인페이지
    @GetMapping("/sign-main")
    public String signMain(){
        return "/sign/sign-main";
    }

    // 회원가입 - 등록
    @PostMapping("/sign-success")
    public String save(SignDto signDto, Model model, HttpServletResponse response) throws Exception {

        SignDto loginid = loginService.findByUserid(signDto);

        // 만약 가입된 아이디가 없으면?
        if(loginid.getUserid() == null) {

            System.out.println("가입된 아이디 없음");

            signService.save(signDto);

            model.addAttribute("success", signDto);

            return "/sign/sign-success";

        }else{

            System.out.println("가입된 아이디가 있음");

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 가입된 아이디입니다.');</script>");

            out.flush();

            return "/sign/sign-main";

        }

    }

}
