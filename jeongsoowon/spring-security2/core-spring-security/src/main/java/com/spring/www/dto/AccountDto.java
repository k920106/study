package com.spring.www.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDto {
    private String id;
    private String username;
    private String password;
    private String email;
    //  private String age;
    private int age;
    //  private String role;
    private List<String> roles;
}
