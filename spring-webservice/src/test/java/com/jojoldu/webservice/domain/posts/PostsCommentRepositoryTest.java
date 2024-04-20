//package com.jojoldu.webservice.domain.posts;
//
//import com.jojoldu.webservice.domain.member.Member;
//import com.jojoldu.webservice.domain.member.MemberRepository;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class PostsCommentRepositoryTest {
//
//    @Autowired
//    PostsRepository postsRepository;
//
//    @Autowired
//    PostsCommentRepository postsCommentRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void findById() {
//
//        Member 강민성 = Member.builder().name("강민성").mobileNumber("01012312312").build();
//        Member save1 = memberRepository.save(강민성);
//
//        Optional<Member> byId = memberRepository.findById(강민성.getId());
//
//        Posts save = postsRepository.save(Posts.builder()
//                .title("테스트 게시글")
//                .content("테스트 본문")
//                .author(byId.get())
//                .build());
//
//        em.clear();
//
//        Posts posts = postsRepository.findById(save.getId()).orElseThrow(IllegalArgumentException::new);
//
//        PostsComment test = postsCommentRepository.save(PostsComment.builder()
//                .comment("test")
//                .listNum(String.valueOf(posts.getId()))
//                .posts(posts)
//                .build());
//        em.clear();
//
//        PostsComment postsComment = postsCommentRepository.findById(test.getId()).orElseThrow(IllegalArgumentException::new);
//
//        postsComment.getPosts().getTitle();
//
//        System.out.println(postsComment.getPosts());
//
//        em.clear();
//
//        System.out.println("!!");
//        List<PostsComment> byPosts = postsCommentRepository.findByPosts(posts);
//        System.out.println("!!");
//
//    }
//}
