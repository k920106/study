package study.querydsl.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

  private String name;

  private int age;

  public UserDto(String name, int age) {
    this.name = name;
    this.age = age;
  }

}
