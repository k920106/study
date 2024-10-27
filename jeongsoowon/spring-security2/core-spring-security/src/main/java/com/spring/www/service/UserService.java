package com.spring.www.service;

import com.spring.www.domain.entity.Account;
import com.spring.www.dto.AccountDto;

import java.util.List;

public interface UserService {
    void createUser(Account account);
    void modifyUser(AccountDto accountDto);
    List<Account> getUsers();
    AccountDto getUser(Long id);
    void deleteUser(Long idx);
    void order();
}
