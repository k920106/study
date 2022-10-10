package kr.tastyhouse.www.auth;

import kr.tastyhouse.www.auth.provider.FacebookUserInfo;
import kr.tastyhouse.www.auth.provider.GoogleUserInfo;
import kr.tastyhouse.www.auth.provider.NaverUserInfo;
import kr.tastyhouse.www.auth.provider.OAuth2UserInfo;
import kr.tastyhouse.www.model.User;
import kr.tastyhouse.www.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private UserRepository userRepository;

  // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
  // 함수 종료 시 @AuthenticationPrincipal 만들어진다.
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//    System.out.println(userRequest.getClientRegistration()); // registrationId로 어떤 OAuth 로 로그인했는지 확인 가능
//    System.out.println(userRequest.getAccessToken().getTokenValue());

    OAuth2User oauth2User = super.loadUser(userRequest);
    // 구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> code 를 리턴(OAuth-Client 라이브러리) -> AccessToken 요청
    // userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필 수신
    System.out.println(super.loadUser(userRequest).getAttributes());

    OAuth2UserInfo oAuth2UserInfo = null;

    // 구글
    if("google".equals(userRequest.getClientRegistration().getRegistrationId())) {
      System.out.println("구글");
      oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
    }
    // 페이스북
    else if("facebook".equals(userRequest.getClientRegistration().getRegistrationId())) {
      System.out.println("페이스북");
      oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
    }
    // 네이버
    else if("naver".equals(userRequest.getClientRegistration().getRegistrationId())) {
      System.out.println("네이버");
      oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
    }
    else {
      System.out.println("구글, 페이스북, 네이버만 지원");
    }

    // 회원가입을 강제로 진행
    String provider = oAuth2UserInfo.getProvider();
    String providerId = oAuth2UserInfo.getProviderId();
    String username = provider + "_" + providerId;
    String password = bCryptPasswordEncoder.encode("겟인데어");
    String email = oAuth2UserInfo.getEmail();
    String role = "ROLE_USER";

    User userEntity = userRepository.findByUsername(username);
    if(userEntity == null) {
      userEntity = User.builder()
                       .username(username)
                       .password(password)
                       .email(email)
                       .role(role)
                       .provider(provider)
                       .providerId(providerId)
                       .build();
      userRepository.save(userEntity);
      System.out.println("회원가입 완료");
    }
    else {
      System.out.println("로그인 완료");
    }

    return new PrincipalDetails(userEntity, oauth2User.getAttributes());
  }
}
