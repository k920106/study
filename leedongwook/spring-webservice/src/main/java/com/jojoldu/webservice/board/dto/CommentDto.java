package com.jojoldu.webservice.board.dto;

import com.jojoldu.webservice.board.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {

    private String comment;
    private String listNum;

    @Builder
    public CommentDto(String comment, String listNum) {
        this.comment = comment;
        this.listNum = listNum;
    }

    public CommentDto(Comment postsComment) {
        this.comment = postsComment.getComment();
        this.listNum = postsComment.getListNum();
    }

    public Comment toEntity() {
        return Comment.builder()
                .comment(comment)
                .listNum(listNum)
                .build();
    }

}
