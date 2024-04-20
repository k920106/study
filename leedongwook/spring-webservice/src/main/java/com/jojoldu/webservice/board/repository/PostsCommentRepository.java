package com.jojoldu.webservice.board.repository;

import com.jojoldu.webservice.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsCommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByListNum(String listNum);

}
