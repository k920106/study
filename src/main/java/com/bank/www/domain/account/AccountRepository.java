package com.bank.www.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // @Query("SELECT ac FROM Account ac JOIN FETCH ac.user u WHERE ac.number =:number")
    Optional<Account> findByNumber(@Param("number") Long number);
}
