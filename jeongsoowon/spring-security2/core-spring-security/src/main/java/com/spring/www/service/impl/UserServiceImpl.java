package com.spring.www.service.impl;

import com.spring.www.domain.Account;
import com.spring.www.repository.UserRepository;
import com.spring.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired private UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
