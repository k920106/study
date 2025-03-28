package com.spring.www.service;

import com.spring.www.domain.entity.Resources;

import java.util.List;

public interface ResourcesService {
    Resources getResources(long id);
    List<Resources> getResources();
    void createResources(Resources Resources);
    void deleteResources(long id);
}
