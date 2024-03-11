package com.bank.www.config.jwt;

import com.bank.www.config.auth.LoginUser;
import com.bank.www.domain.user.User;
import com.bank.www.domain.user.UserEnum;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtProcessTest {
    @Test
    public void create_test() throws Exception {
        // given
//        User user = User.builder().id(1L).role(UserEnum.CUSTOMER).build();
//        User user = User.builder().id(2L).role(UserEnum.ADMIN).build();
//        LoginUser loginUser = new LoginUser(user);

        // when
//        String jwtToken = JwtProcess.create(loginUser);
        String jwtToken = createToken();
        System.out.println("테스트 : " + jwtToken);

        // then
        assertTrue(jwtToken.startsWith(JwtVO.TOKEN_PREFIX));
    }

    @Test
    public void verify_test() throws Exception {
        // given
//        String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYW5rIiwicm9sZSI6IkNVU1RPTUVSIiwiaWQiOjEsImV4cCI6MTcxMDU1NDQ0NH0.pwPZZM5udUPWh5MWWC-fqmse4inN3n-OZ1t8TcOuRs0MQiFvuGkphUFQzXoW8VnEACASkZoy7ighVXBUKMHsRA";
//        String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYW5rIiwicm9sZSI6IkFETUlOIiwiaWQiOjIsImV4cCI6MTcxMDU1NDkzMH0.oQJG0EFj8TLLggeGzU-x_D1fdlONectfI02PsTLEXhpvGwvOlSjvWN-YfK0iFSta-ZrRIskgTI1I97bIklS9zQ";
        String token = createToken(); // Bearer 제거해서 처리하기
        String jwtToken = token.replace(JwtVO.TOKEN_PREFIX, "");

        // when
        LoginUser loginUser = JwtProcess.verify(jwtToken);
        System.out.println("테스트 : " + loginUser.getUser().getId());
        System.out.println("테스트 : " + loginUser.getUser().getRole().name());

        // then
        assertThat(loginUser.getUser().getId()).isEqualTo(1L);
        assertThat(loginUser.getUser().getRole()).isEqualTo(UserEnum.CUSTOMER);
//        assertThat(loginUser.getUser().getId()).isEqualTo(2L);
//        assertThat(loginUser.getUser().getRole()).isEqualTo(UserEnum.ADMIN);
    }

    private String createToken() {
        // given
        User user = User.builder().id(1L).role(UserEnum.CUSTOMER).build();
        LoginUser loginUser = new LoginUser(user);

        // when
        return JwtProcess.create(loginUser);
    }
}
