package io.security.basicsecurity.security.service;

import io.security.basicsecurity.domain.entity.Account;
import io.security.basicsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = userRepository.findByUsername(username);

    if(account == null) {
      throw new UsernameNotFoundException("UsernameNotFoundException");
    }

    Set<String> userRoles = account.getUserRoles()
            .stream()
            .map(userRole -> userRole.getRoleName())
            .collect(Collectors.toSet());
//    List<GrantedAuthority> roles = new ArrayList<>();
//    roles.add(new SimpleGrantedAuthority(account.getRole()));

    List<GrantedAuthority> collect = userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    AccountContext accountContext = new AccountContext(account, collect);
//    AccountContext accountContext = new AccountContext(account, roles);

    return accountContext;
  }
}
