package com.bank.www.dto.account;

import com.bank.www.domain.account.Account;
import com.bank.www.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountReqDto {
    @Setter
    @Getter
    public static class AccountSaveReqDto {
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long number;
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long password;

        public Account toEntity(User user) {
            return Account.builder()
                          .number(number)
                          .password(password)
                          .balance(1000L)
                          .user(user)
                          .build();
        }
    }

    @Setter
    @Getter
    public static class AccountDepositReqDto {
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long number;

        @NotNull
        private Long amount; // 0원 유효성 검사

        @NotEmpty
        @Pattern(regexp = "DEPOSIT")
        private String gubun; // DEPOSIT

        @NotEmpty
        @Pattern(regexp = "^[0-9]{11}")
        private String tel;
    }

    @Setter
    @Getter
    public static class AccountWithdrawReqDto {
        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long number;

        @NotNull
        @Digits(integer = 4, fraction = 4)
        private Long password;

        @NotNull
        private Long amount;

        @NotEmpty
        @Pattern(regexp = "WITHDRAW")
        private String gubun;
    }
}
