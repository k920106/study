//package com.jojoldu.webservice;
//
//import com.jojoldu.webservice.domain.member.Member;
//import com.jojoldu.webservice.domain.posts.Posts;
//import com.jojoldu.webservice.domain.posts.PostsRepository;
//import com.jojoldu.webservice.dto.posts.PostsSaveRequestDto;
//import com.jojoldu.webservice.service.PostsService;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PostServiceTest {
//
//    @Autowired
//    private PostsService postsService;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @After
//    public void cleanup() {
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    public void Dto데이터가_posts테이블에_저장된다() {
//
//        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
//                .author(Member.builder().name("강민성").mobileNumber("01012312312").build())
//                .content("테스트")
//                .title("테스트 타이블")
//                .build();
//
//        postsService.save(dto);
//
//        Posts posts = postsRepository.findAll().get(0);
//        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
//        assertThat(posts.getContent()).isEqualTo(dto.getContent());
//        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
//
//    }
//
//}
