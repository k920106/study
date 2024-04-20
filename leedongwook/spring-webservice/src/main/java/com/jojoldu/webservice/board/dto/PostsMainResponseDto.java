package com.jojoldu.webservice.board.dto;

import com.jojoldu.webservice.board.entity.Board;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsMainResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private String modifiedDate;


    public PostsMainResponseDto(Board entity) {
        id = entity.getId();
        title = entity.getTitle();
        author = entity.getSign().getUserid();
        content = entity.getContent();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    private String toStringDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

}
