package com.spring.www.security.factory;

import com.spring.www.security.service.SecurityResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
public class MethodResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {
    private SecurityResourceService securityResourceService;
    private LinkedHashMap<String, List<ConfigAttribute>> resourcesMap;

    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    public void init() {
        resourcesMap = securityResourceService.getMethodResourceList();
    }

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getObject() {
        if (resourcesMap == null) {
            init();
        }
        return resourcesMap;
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
