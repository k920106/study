package com.jojoldu.webservice.board.web;

import com.jojoldu.webservice.board.dto.BoardDto;
import com.jojoldu.webservice.board.dto.CommentDto;
import com.jojoldu.webservice.board.dto.PostsMainResponseDto;
import com.jojoldu.webservice.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class BoardRestController {

    private BoardService boardService;

    // 게시글 등록
    @PostMapping("/posts")
    public void savePosts(@RequestBody BoardDto boardDto) {
        boardService.save(boardDto);
    }

    // 상세 보기
    @PostMapping("/info")
    public PostsMainResponseDto info(@RequestBody PostsMainResponseDto postsMainResponseDto) {

        PostsMainResponseDto goview = boardService.info(postsMainResponseDto);

        return goview;
    }

    // 댓글 등록
    @PostMapping("/saveComment")
    public void saveCommentPosts(@RequestBody CommentDto postsCommentResponseDto) {
        boardService.saveCommnet(postsCommentResponseDto);
    }

    // 댓글 보기
    @GetMapping("/infocomment/{num}")
    public List<CommentDto> commentList(@PathVariable("num") String num) {

        List<CommentDto> list = new ArrayList();

        list = boardService.findByListName(new CommentDto(null, num));

        return list;
    }

}