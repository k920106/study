package com.spring.www.service.impl;

import com.spring.www.domain.entity.Account;
import com.spring.www.domain.entity.Role;
import com.spring.www.dto.AccountDto;
import com.spring.www.repository.RoleRepository;
import com.spring.www.repository.UserRepository;
import com.spring.www.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(Account account) {
        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setUserRoles(roles);

        userRepository.save(account);
    }

    @Transactional
    @Override
    public void modifyUser(AccountDto accountDto) {
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);

        if (accountDto.getRoles() != null) {
            Set<Role> roles = new HashSet<>();
            accountDto.getRoles().forEach(role -> {
                Role r = roleRepository.findByRoleName(role);
                roles.add(r);
            });
            account.setUserRoles(roles);
        }
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        userRepository.save(account);

    }

    @Transactional
    public AccountDto getUser(Long id) {
        Account account = userRepository.findById(id).orElse(new Account());
        ModelMapper modelMapper = new ModelMapper();
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);

        List<String> roles = account.getUserRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());
        accountDto.setRoles(roles);

        return accountDto;
    }

    @Transactional
    public List<Account> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    //@Secured("ROLE_MANAGER")
    @Secured("ROLE_USER")
    public void order() {
        System.out.println("order");
    }
}
