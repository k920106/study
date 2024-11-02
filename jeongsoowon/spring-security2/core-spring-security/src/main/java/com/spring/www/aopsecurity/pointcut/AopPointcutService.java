package com.spring.www.aopsecurity.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AopPointcutService {
    public void notSecured() {
        log.debug("notSecured");
    }

    public void pointcutSecured() {
        log.debug("pointcutSecured");
    }
}
