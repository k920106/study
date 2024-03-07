package com.bank.www.dto.user;

import com.bank.www.domain.user.User;
import com.bank.www.domain.user.UserEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserReqDto {
    @Setter
    @Getter
    public static class JoinReqDto {
        private String username;
        private String password;
        private String email;
        private String fullname;

        public User toEntity(BCryptPasswordEncoder passwordEncoder) {
            return User.builder()
                       .username(username)
                       .password(passwordEncoder.encode(password))
                       .email(email)
                       .fullname(fullname)
                       .role(UserEnum.CUSTOMER)
                       .build();
        }
    }
}
