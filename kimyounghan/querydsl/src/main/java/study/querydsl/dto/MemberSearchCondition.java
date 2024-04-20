package study.querydsl.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSearchCondition {

  private String userName; // 회원명

  private String teamName; // 팀명

  private Integer ageGoe; // 최소 나이

  private Integer ageLoe; // 최대 나이

}
