package kr.tastyhouse.www.auth;


import kr.tastyhouse.www.model.User;
import kr.tastyhouse.www.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  // 시큐리티 session(내부 Authentication(내부 UserDetails))
  // 함수 종료 시 @AuthenticationPrincipal 만들어진다.
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User userEntity = userRepository.findByUsername(username);
    if(userEntity != null) {
      return new PrincipalDetails(userEntity);
    }
    return null;
  }
}
