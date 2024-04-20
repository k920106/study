package com.jojoldu.webservice.list.web;

import com.jojoldu.webservice.board.service.BoardService;
import com.jojoldu.webservice.sign.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ListRestController {

  private SignService signService;
  private BoardService boardService;

//  @PostMapping("/list-edit/{id}")
//  public SignDto listEdit(@PathVariable("id") Long id) {
//
//    System.out.println("성공");
//
//    // SignDto signDto = signService.listEdit(new SignDto(null, id));
//
//    return null;
//
//  }


}
