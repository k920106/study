package com.jojoldu.webservice.list.web;

import com.jojoldu.webservice.sign.dto.SignDto;
import com.jojoldu.webservice.sign.service.SignService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ListController {

  private SignService signService;

  // 회원리스트 - 조회
  @GetMapping("list-main")
  public String findusers(Model model) {

    List<SignDto> userall = signService.findusers();

    model.addAttribute("users", userall);

    return "list/list-main";

  }

}
