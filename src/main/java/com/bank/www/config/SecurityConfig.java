package com.bank.www.config;

import com.bank.www.domain.user.UserEnum;
import com.bank.www.dto.ResponseDto;
import com.bank.www.util.CustomResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class SecurityConfig {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.debug("디버그 : BCryptPasswordEncoder 빈 등록됨");

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("디버그 : filterChain 빈 등록됨");

        http.headers().frameOptions().sameOrigin(); // iframe 허용안함
        http.csrf().disable(); // enable이면 post맨 작동안함 (메타코딩 유튜브에 시큐리티 강의)
        http.cors().configurationSource(configurationSource());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // jSessionId를 서버쪽에서 관리안하겠다는 뜻
        http.formLogin().disable(); // react, 앱으로 요청할 예정
        http.httpBasic().disable(); // httpBasic은 브라우저가 팝업창을 이용해서 사용자 인증을 진행한다

        http.authorizeRequests()
            .antMatchers("/api/s/**").authenticated()
            .antMatchers("/api/admin/**").hasRole("" + UserEnum.ADMIN)
            .anyRequest().permitAll();

        // 인증 실패
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            CustomResponseUtil.unAuthentication(response, "로그인을 진행해 주세요");
//            String uri = request.getRequestURI();
//            log.debug("디버그: " + uri);
//            if (uri.contains("admin")) {
//                CustomResponseUtil.unAuthorization(response, "관리자로 로그인을 진행해 주세요");
//            }
//            else {
//                CustomResponseUtil.unAuthentication(response, "로그인을 진행해 주세요");
//            }
        });

        return http.build();
    }

    public CorsConfigurationSource configurationSource() {
        log.debug("디버그 : configurationSource cors 설정이 SecurityFilterChain에 등록됨");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*"); // 모든 Header 요청 허용
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (Javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용 (프론트 앤드 IP만 허용 react)
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용
        configuration.addExposedHeader("Authorization"); // 옛날에는 디폴트 였다. 지금은 아닙니다.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
