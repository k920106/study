package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 500, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  private String author;

  @Builder
  public Posts(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public void allUpdate(PostsUpdateRequestDto dto) {
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }
}
