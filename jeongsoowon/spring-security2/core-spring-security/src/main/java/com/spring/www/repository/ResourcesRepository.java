package com.spring.www.repository;

import com.spring.www.domain.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    @Query("SELECT r FROM Resources r JOIN FETCH r.roleSet WHERE r.resourceType = 'url' ORDER BY r.orderNum DESC")
    List<Resources> findAllResources();

    Resources findByResourceNameAndHttpMethod(String resourceName, String httpMethod);
}
