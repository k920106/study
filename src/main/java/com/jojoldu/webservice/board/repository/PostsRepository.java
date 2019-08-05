package com.jojoldu.webservice.board.repository;

import com.jojoldu.webservice.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Board, Long> {

    @Query(value = "SELECT p " +
            "FROM Board p " +
            "ORDER BY p.id DESC")
    Stream<Board> findAllDesc();


}