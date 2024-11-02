package com.spring.www.security.configs;

import com.spring.www.security.factory.MethodResourcesMapFactoryBean;
import com.spring.www.security.service.SecurityResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Autowired private SecurityResourceService securityResourceService;

    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        //return new MapBasedMethodSecurityMetadataSource();
        return mapBasedMethodSecurityMetadataSource();
    }

    @Bean
    public MapBasedMethodSecurityMetadataSource mapBasedMethodSecurityMetadataSource() {
        return new MapBasedMethodSecurityMetadataSource(methodResourcesMapFactoryBean().getObject());
    }

    @Bean
    public MethodResourcesMapFactoryBean methodResourcesMapFactoryBean() {
        MethodResourcesMapFactoryBean methodResourcesMapFactoryBean = new MethodResourcesMapFactoryBean();
        methodResourcesMapFactoryBean.setSecurityResourceService(securityResourceService);
        return methodResourcesMapFactoryBean;
    }
}
