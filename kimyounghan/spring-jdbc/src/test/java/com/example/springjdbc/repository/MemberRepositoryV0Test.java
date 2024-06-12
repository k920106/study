package com.example.springjdbc.repository;

import com.example.springjdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("김영한5", 10000);
        repository.save(member);

        String memberId = member.getMemberId();

        // findById
        Member findMember = repository.findById(memberId);
        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(member);

        // update: money: 10000 -> 20000
        repository.update(memberId, 20000);
        Member updatedMember = repository.findById(memberId);
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        // delete
        repository.delete(memberId);
        assertThatThrownBy(() -> repository.findById(memberId)).isInstanceOf(NoSuchElementException.class);
    }
}
