package com.jojoldu.webservice.board.web;

import com.jojoldu.webservice.board.dto.BoardDto;
import com.jojoldu.webservice.board.dto.CommentDto;
import com.jojoldu.webservice.board.dto.PostsMainResponseDto;
import com.jojoldu.webservice.board.service.BoardService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BoardRestController {

    private BoardService boardService;

    // 게시글 등록
    @PostMapping("/posts")
    public void savePosts(@RequestBody BoardDto boardDto, HttpServletRequest request) {
        boardService.save(boardDto, request);
    }

    // 게시글 상세
    @PostMapping("/info")
    public PostsMainResponseDto info(@RequestBody PostsMainResponseDto postsMainResponseDto) {

        PostsMainResponseDto goview = boardService.info(postsMainResponseDto);

        return goview;
    }

    // 게시글 수정
    @PostMapping("/list-edit/{id}")
    public void updatePost(@PathVariable("id") String id, @RequestBody BoardDto boardDto){
        Long lid = Long.parseLong(id);
        boardService.save(boardDto, lid);
    }

    // 게시글 삭제
    @GetMapping("/list-delete/{id}")
    public void updatePost(@PathVariable("id") String id){
        Long lid = Long.parseLong(id);
        boardService.delete(lid);
    }

    // 댓글 등록
    @PostMapping("/saveComment")
    public void saveCommentPosts(@RequestBody CommentDto postsCommentResponseDto) {
        boardService.saveCommnet(postsCommentResponseDto);
    }

    // 댓글 조회
    @GetMapping("/infocomment/{num}")
    public List<CommentDto> commentList(@PathVariable("num") String num) {

        List<CommentDto> list = new ArrayList<>();

        list = boardService.findByListName(new CommentDto(null, num));

        return list;
    }

}