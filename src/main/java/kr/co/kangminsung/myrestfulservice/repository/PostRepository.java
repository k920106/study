package kr.co.kangminsung.myrestfulservice.repository;

import kr.co.kangminsung.myrestfulservice.dao.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
