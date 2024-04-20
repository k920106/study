package com.company;

import com.company.kr.inflearn.BookDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Project01_A {
    public static void main(String[] args) {
        // Object(BookDTO) -> JSON(String)
        BookDTO dto = new BookDTO("자바", 21000, "에이콘", 670);

        Gson g = new Gson();
        String json = g.toJson(dto);
        System.out.println(json);

        // JSON(String) -> Object(BookDTO)
        BookDTO dto1 = g.fromJson(json, BookDTO.class);
        System.out.println(dto1);
        System.out.println(dto1.getTitle() + "\t" + dto1.getPrice());

        // Object(List<BookDto>) -> JSON(String) : [ {    }, {    }....]
        List<BookDTO> lst = new ArrayList<BookDTO>();
        lst.add(new BookDTO("자바1", 21000, "에어콘1", 570));
        lst.add(new BookDTO("자바2", 31000, "에어콘2", 670));
        lst.add(new BookDTO("자바3", 11000, "에어콘3", 370));

        String lstJson = g.toJson(lst);
        System.out.println(lstJson);


        // JSON(String) -> Object(List<BookDto>)
        List<BookDTO> lst1 = g.fromJson(lstJson, new TypeToken<List<BookDTO>>(){}.getType());
        for (BookDTO vo : lst1) {
            System.out.println(vo);
        }
    }
}
