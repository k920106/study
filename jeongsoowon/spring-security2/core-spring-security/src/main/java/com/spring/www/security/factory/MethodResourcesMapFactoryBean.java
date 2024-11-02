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
    private String resourceType;

    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void init() {
        //resourcesMap = securityResourceService.getMethodResourceList();
        if ("method".equals(resourceType)) {
            resourcesMap = securityResourceService.getMethodResourceList();
        }
        else if ("pointcut".equals(resourceType)) {
            resourcesMap = securityResourceService.getPointcutResourceList();
        }
        else {
            log.error("resourceType must be 'method' or 'pointcut'");
        }
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
