package com.example.springjdbc.repository;

import com.example.springjdbc.domain.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // Member member = new Member("김영한1", 10000);
        Member member = new Member("김영한2", 10000);
        repository.save(member);
    }
}
