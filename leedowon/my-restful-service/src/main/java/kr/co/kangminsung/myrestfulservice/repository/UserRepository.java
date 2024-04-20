package kr.co.kangminsung.myrestfulservice.repository;

import kr.co.kangminsung.myrestfulservice.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
