package com.spring.www.service.impl;

import com.spring.www.domain.entity.Role;
import com.spring.www.repository.RoleRepository;
import com.spring.www.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired private RoleRepository roleRepository;

    @Transactional
    public Role getRole(long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    @Transactional
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }
}
