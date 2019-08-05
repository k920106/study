package com.jojoldu.webservice.login.web;

import com.jojoldu.webservice.login.service.LoginService;
import com.jojoldu.webservice.sign.dto.SignDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @GetMapping("/login-main")
    public String loginMain(){
        return "/login/login-main";
    }

    @PostMapping("/login-check")
    public String loginSuccess(SignDto signDto, HttpServletResponse response) throws Exception {

        SignDto loginid = loginService.findByUserid(signDto);

        // 만약 가입된 아이디가 없으면?
        if(loginid.getUserid() == null) {

            System.out.println("가입된 아이디 없음");

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('가입된 아이디가 없습니다');</script>");

            out.flush();

            return "/login/login-main";

        }else{

            SignDto loginidpw = loginService.findByUseridAndPw(signDto);

            // 만약 비밀번호가 틀리면?
            if(loginidpw.getPw() == null) {

                System.out.println("비밀번호 틀림");

                response.setContentType("text/html; charset=UTF-8");

                PrintWriter out = response.getWriter();

                out.println("<script>alert('비밀번호 틀렸습니다');</script>");

                out.flush();

                return "/login/login-main";

            }else{

                System.out.println("로그인 성공");

                return "/main";
            }
        }

    }

}
