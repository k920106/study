package com.jojoldu.webservice.board.service;

import com.jojoldu.webservice.board.dto.BoardDto;
import com.jojoldu.webservice.board.dto.CommentDto;
import com.jojoldu.webservice.board.dto.PostsMainResponseDto;
import com.jojoldu.webservice.board.entity.Board;
import com.jojoldu.webservice.board.entity.Comment;
import com.jojoldu.webservice.board.repository.PostsCommentRepository;
import com.jojoldu.webservice.board.repository.PostsRepository;
import com.jojoldu.webservice.sign.service.SignService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class BoardService {

    private PostsRepository postsRepository;
    private PostsCommentRepository postsCommentRepository;
    private SignService signService;

    // 게시글 등록
    @Transactional
    public Long save(BoardDto boardDto, HttpServletRequest request) {

        HttpSession session = request.getSession();

        String userid = (String)session.getAttribute("userid");

        Board b = boardDto.toEntity();

        b.setSign(signService.findByUserId(userid));

        return postsRepository.save(b).getId();
    }

    // 댓글 등록
//    @Transactional
//    public Long saveCommnet(CommentDto commentDto) {
//        return postsCommentRepository.save(commentDto.toEntity()).getId();
//    }

    // 게시글 조회
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시글 상세
    @Transactional(readOnly = true)
    public PostsMainResponseDto info(PostsMainResponseDto postsMainResponseDto) {

        Board boardEntity = postsRepository.findById(postsMainResponseDto.getId())
            .orElse(Board.builder().build());

        return new PostsMainResponseDto(boardEntity);
    }

    // 게시글 수정
    @Transactional
    public String save(BoardDto boardDto, Long id) {

        Optional<Board> b = postsRepository.findById(id);

        Board board = b.get();

        board.setContent(boardDto.getContent());

        board.setTitle(boardDto.getTitle());

        postsRepository.save(board);

        return "성공";
    }

    // 게시글 삭제
    @Transactional
    public String delete(Long id){

        Optional<Board> b = postsRepository.findById(id);

        Board board = b.get();

        postsRepository.delete(board);

        return "삭제";
    }

    // 댓글 등록
    @Transactional
    public Long saveCommnet(CommentDto commentDto) {
        return postsCommentRepository.save(commentDto.toEntity()).getId();
    }

    // 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentDto> findByListName(CommentDto commentDto) {

        List<Comment> commentEntity = postsCommentRepository.findByListNum(commentDto.getListNum());

        List<CommentDto> collect = commentEntity.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());

        return collect;
    }

}
