package com.spring.www.config;

import com.spring.www.repository.AccessIpRepository;
import com.spring.www.repository.ResourcesRepository;
import com.spring.www.security.service.SecurityResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    //public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository) {
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository, AccessIpRepository accessIpRepository) {
        //SecurityResourceService securityResourceService = new SecurityResourceService(resourcesRepository);
        SecurityResourceService securityResourceService = new SecurityResourceService(resourcesRepository, accessIpRepository);
        return securityResourceService;
    }
}
