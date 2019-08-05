package com.jojoldu.webservice.board.service;

import com.jojoldu.webservice.board.dto.BoardDto;
import com.jojoldu.webservice.board.dto.CommentDto;
import com.jojoldu.webservice.board.dto.PostsMainResponseDto;
import com.jojoldu.webservice.board.entity.Board;
import com.jojoldu.webservice.board.entity.Comment;
import com.jojoldu.webservice.board.repository.PostsCommentRepository;
import com.jojoldu.webservice.board.repository.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BoardService {

    private PostsRepository postsRepository;
    private PostsCommentRepository postsCommentRepository;

    // Controller - 조회
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }

    // RestController - 게시글 등록
    @Transactional
    public Long save(BoardDto boardDto) {
        return postsRepository.save(boardDto.toEntity()).getId();
    }

    // RestController - 상세 보기
    @Transactional(readOnly = true)
    public PostsMainResponseDto info(PostsMainResponseDto postsMainResponseDto) {

        Board boardEntity = postsRepository.findById(postsMainResponseDto.getId()).orElse(Board.builder().build());

        return new PostsMainResponseDto(boardEntity);
    }

    // RestController - 댓글 등록
    @Transactional
    public Long saveCommnet(CommentDto commentDto) {
        return postsCommentRepository.save(commentDto.toEntity()).getId();
    }

    // RestController - 댓글 보기
    @Transactional(readOnly = true)
    public List<CommentDto> findByListName(CommentDto commentDto) {

        List<Comment> commentEntity = postsCommentRepository.findByListNum(commentDto.getListNum());

        List<CommentDto> collect = commentEntity.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());

        return collect;
    }

}
