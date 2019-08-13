//package com.jojoldu.webservice.domain.posts;
//
//import com.jojoldu.webservice.board.repository.PostsRepository;
//import com.jojoldu.webservice.domain.member.Member;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class PostsRepositoryTest {
//
//    @Autowired
//    PostsRepository postsRepository;
//
//    @Autowired
//    EntityManager em;
//
//    @After
//    public void cleaup(){
//        //postsRepository.deleteAll();
//    }
//
//    @Test
//    public void 게시글저장_블러오기() {
//        postsRepository.save(Posts.builder()
//                .title("테스트 게시글")
//                .content("테스트 본문")
//                .author(Member.builder().name("강민성").mobileNumber("01012312312").build())
//                .build());
//
//    List<Posts> postsLists = postsRepository.findAll();
//
//    Posts posts = postsLists.get(0);
//
//    posts.getAuthor().getName();
//
//    assertThat(posts.getTitle(), is("테스트 게시글"));
//    assertThat(posts.getContent(), is("테스트 본문"));
//
//    }
//
//    @Test
//    public void BaseTimeEntity_등록() {
//        LocalDateTime now = LocalDateTime.now();
//        postsRepository.save(Posts.builder()
//                .title("테스트 게시글")
//                .content("테스트 본문")
//                .author(Member.builder().name("강민성").mobileNumber("01012312312").build())
//                .build());
//
//        List<Posts> postsList = postsRepository.findAll();
//
//        Posts posts = postsList.get(0);
//        assertTrue(posts.getCreatedDate().isAfter(now));
//        assertTrue(posts.getModifiedDate().isAfter(now));
//    }
//
////    @Test
////    public void comment저장_불러오기() {
////
////        List<PostsComment> potsCommentsList = postsRepository.findAll();
////
////        PostsComment postsComment = potsCommentsList.get(0);
////        assertThat(postsComment.getId(), is(1));
////        assertThat(postsComment.getComment(), is("댓글"));
////    }
//
//    @Test
//    public void findById() {
//        Posts save = postsRepository.save(Posts.builder()
//                .title("테스트 게시글")
//                .content("테스트 본문")
//                .author(Member.builder().name("강민성").mobileNumber("01012312312").build())
//                .build());
//
//        em.clear();
//
//        postsRepository.findById(save.getId());
//    }
//}
