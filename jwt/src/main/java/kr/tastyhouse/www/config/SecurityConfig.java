package kr.tastyhouse.www.config;

import kr.tastyhouse.www.auth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// 1. 코드받기(인증)
// 2. 엑세스 토큰(권한)
// 3. 사용자 프로필 정보 받기
// 4-1. 정보를 이용하여 회원가입을 자동으로 진행
// 4-2. 쇼핑몰 (이메일, 전화번호, 이름, 아이디, 집주소) / 백화점몰 (vip등급, 일반등급)

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됩니다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // securedEnabled => @secured 활성화 / prePostEnabled => @PreAuthorize, @PostAuthorize 활성화
public class SecurityConfig {

  @Autowired
  private PrincipalOauth2UserService principalOauth2UserService;

  // 해당 메서드의 리턴되는 오브젝트를 Ioc로 등록해준다.
//  @Bean
//  public BCryptPasswordEncoder encoderPwd() {
//    return new BCryptPasswordEncoder();
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests()
            .antMatchers("/user/**").authenticated() // 인증이 되면 접근할 수 있는 주소
            .antMatchers("/manager/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/loginForm")
            .loginProcessingUrl("/login") // login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행한다.
            .defaultSuccessUrl("/")
            .and()
            .oauth2Login()
            .loginPage("/loginForm")
            .and()
            .oauth2Login()
            .loginPage("/loginForm")
            .userInfoEndpoint()
            .userService(principalOauth2UserService); // 구글 로그인이 완료된 뒤의 후처리가 필요하다
    return http.build();
  }

}
