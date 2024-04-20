package com.bank.www.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // @Query("SELECT ac FROM Account ac JOIN FETCH ac.user u WHERE ac.number =:number")
    Optional<Account> findByNumber(@Param("number") Long number);

    // @Query(SELECT ac from Account ac where ac.user_id = :id)
    List<Account> findByUser_id(Long id);
}
