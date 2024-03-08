package com.bank.www.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

// java.util.regex.Pattern
public class RegexTest {

    @Test
    public void 한글만된다_test() throws Exception {
        String regex = "^[가-힣]+$";
        String value = "한글";
        boolean result = Pattern.matches(regex, value);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 한글은안된다_test() throws Exception {

    }

    @Test
    public void 영어만된다_test() throws Exception {

    }

    @Test
    public void 영어는안된다_test() throws Exception {

    }

    @Test
    public void 영어와숫자만된다_test() throws Exception {

    }

    @Test
    public void 영어만되고_길이는최소2최대4이다_test() throws Exception {

    }

    @Test
    public void user_username_test() throws Exception {

    }

    @Test
    public void user_fullname_test() throws Exception {

    }

    @Test
    public void user_email_test() throws Exception {

    }

    @Test
    public void account_gubun_test1() throws Exception {

    }

    @Test
    public void account_gubun_test2() throws Exception {

    }

    @Test
    public void account_tel_test1() throws Exception {

    }

    @Test
    public void account_tel_test2() throws Exception {

    }
}
