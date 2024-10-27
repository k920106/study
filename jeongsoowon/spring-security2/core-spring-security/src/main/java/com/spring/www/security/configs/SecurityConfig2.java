package com.spring.www.security.configs;

import com.spring.www.security.common.FormAuthenticationDetailsSource;
import com.spring.www.security.factory.UrlResourcesMapFactoryBean;
import com.spring.www.security.handler.AjaxAuthenticationFailureHandler;
import com.spring.www.security.handler.AjaxAuthenticationSuccessHandler;
import com.spring.www.security.handler.CustomAccessDeniedHandler;
import com.spring.www.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import com.spring.www.security.provider.FormAuthenticationProvider;
import com.spring.www.security.service.SecurityResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.Arrays;
import java.util.List;

@Order(0)
@Configuration
@EnableWebSecurity
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
    @Autowired private SecurityResourceService securityResourceService;
    @Autowired private FormAuthenticationDetailsSource authenticationDetailsSource;
    @Autowired private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public FilterSecurityInterceptor customFilterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
        filterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean());
        return filterSecurityInterceptor;
    }

    private AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
        return affirmativeBased;
    }

    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() throws Exception {
        //return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject());
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject(), securityResourceService);
    }

    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {
        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
        urlResourcesMapFactoryBean.setSecurityResourceService(securityResourceService);
        return urlResourcesMapFactoryBean;
    }

    private List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
        return Arrays.asList(new RoleVoter());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new FormAuthenticationProvider(passwordEncoder());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
    }

    @Bean
    public AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
           .requestMatchers(PathRequest.toStaticResources()
                                       .atCommonLocations());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/mypage").hasRole("USER")
            .antMatchers("/messages").hasRole("MANAGER")
            .antMatchers("/config").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .authenticationDetailsSource(authenticationDetailsSource)
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
            .permitAll()
        ;
        http.exceptionHandling()
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
            .accessDeniedPage("/denied")
            .accessDeniedHandler(accessDeniedHandler())
            .and()
            .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)
        ;
        http.csrf()
            .disable();

        customConfigurer(http);
    }

    private void customConfigurer(HttpSecurity http) throws Exception {
        http.apply(new AjaxLoginConfigurer<>())
            .successHandlerAjax(ajaxAuthenticationSuccessHandler())
            .failureHandlerAjax(ajaxAuthenticationFailureHandler())
            .setAuthenticationManager(authenticationManagerBean())
            .loginProcessingUrl("/api/login");
    }
}
