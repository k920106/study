package com.bank.www.config.dummy;

import com.bank.www.domain.account.Account;
import com.bank.www.domain.user.User;
import com.bank.www.domain.user.UserEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class DummyObject {
    protected static User newUser(String username, String fullname) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        return User.builder()
                   .username(username)
                   .password(encPassword)
                   .email(username + "@nate.com")
                   .fullname(fullname)
                   .role(UserEnum.CUSTOMER)
                   .build();
    }

    protected User newMockUser(Long id, String username, String fullname) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        return User.builder()
                   .id(id)
                   .username(username)
                   .password(encPassword)
                   .email(username + "@nate.com")
                   .fullname(fullname)
                   .role(UserEnum.CUSTOMER)
                   .createdAt(LocalDateTime.now())
                   .updatedAt(LocalDateTime.now())
                   .build();
    }

    protected Account newMockAccount(Long id, Long number, Long balance, User user) {
        return Account.builder()
                      .id(id)
                      .number(number)
                      .password(1234L)
                      .balance(balance)
                      .user(user)
                      .createdAt(LocalDateTime.now())
                      .updatedAt(LocalDateTime.now())
                      .build();
    }
}
