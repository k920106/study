package com.spring.www.security.service;

import com.spring.www.domain.entity.Account;
import com.spring.www.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        //List<GrantedAuthority> roles = new ArrayList<>();
        //roles.add(new SimpleGrantedAuthority(String.valueOf(account.getRole())));
        Set<String> userRoles = account.getUserRoles()
                                       .stream()
                                       .map(userRole -> userRole.getRoleName())
                                       .collect(Collectors.toSet());

        List<GrantedAuthority> roles = userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        AccountContext accountContext = new AccountContext(account, roles);

        return accountContext;
    }
}
