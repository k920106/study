package com.example.springjdbc.repository;

import com.example.springjdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

/**
 * JdbcTemplate 사용
 */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository {
    // private final DataSource dataSource;
    // private final SQLExceptionTranslator exTranslator;
    private final JdbcTemplate template;

    public MemberRepositoryV5(DataSource dataSource) {
        // this.dataSource = dataSource;
        // this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "INSERT INTO member(member_id, money) VALUES (?, ?)";
        template.update(sql, member.getMemberId(), member.getMoney());
        return member;

        // Connection con = null;
        // PreparedStatement pstmt = null;

        // try {
        //     con = getConnection();
        //     pstmt = con.prepareStatement(sql);
        //     pstmt.setString(1, member.getMemberId());
        //     pstmt.setInt(2, member.getMoney());
        //     pstmt.executeUpdate();
        //     return member;
        // } catch (SQLException e) {
        //     throw exTranslator.translate("save", sql, e);
        // } finally {
        //     close(con, pstmt, null);
        // }
    }

    @Override
    public Member findById(String memberId) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        return template.queryForObject(sql, memberRowMapper(), memberId);

        // Connection con = null;
        // PreparedStatement pstmt = null;
        // ResultSet rs = null;

        // try {
        //     con = getConnection();
        //     pstmt = con.prepareStatement(sql);
        //     pstmt.setString(1, memberId);

        //     rs = pstmt.executeQuery();
        //     if (rs.next()) {
        //         Member member = new Member();
        //         member.setMemberId(rs.getString("member_id"));
        //         member.setMoney(rs.getInt("money"));
        //         return member;
        //     } else {
        //         throw new NoSuchElementException("member not found memberId=" + memberId);
        //     }
        // } catch (SQLException e) {
        //     throw exTranslator.translate("findById", sql, e);
        // } finally {
        //     close(con, pstmt, rs);
        // }
    }

    @Override
    public void update(String memberId, int money) {
        String sql = "UPDATE member SET money=? WHERE member_id=?";
        template.update(sql, money, memberId);

        // Connection con = null;
        // PreparedStatement pstmt = null;

        // try {
        //     con = getConnection();
        //     pstmt = con.prepareStatement(sql);
        //     pstmt.setInt(1, money);
        //     pstmt.setString(2, memberId);
        //     int resultSize = pstmt.executeUpdate();
        //     log.info("resultSize={}", resultSize);
        // } catch (SQLException e) {
        //     throw exTranslator.translate("update", sql, e);
        // } finally {
        //     close(con, pstmt, null);
        // }
    }

    @Override
    public void delete(String memberId) {
        String sql = "DELETE FROM member WHERE member_id=?";
        template.update(sql, memberId);

        // Connection con = null;
        // PreparedStatement pstmt = null;

        // try {
        //     con = getConnection();
        //     pstmt = con.prepareStatement(sql);
        //     pstmt.setString(1, memberId);
        //     pstmt.executeUpdate();
        // } catch (SQLException e) {
        //     throw exTranslator.translate("delete", sql, e);
        // } finally {
        //     close(con, pstmt, null);
        // }
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setMoney(rs.getInt("money"));
            return member;
        };
    }

    // private void close(Connection con, Statement stmt, ResultSet rs) {
    //     JdbcUtils.closeResultSet(rs);
    //     JdbcUtils.closeStatement(stmt);
    //     // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
    //     DataSourceUtils.releaseConnection(con, dataSource);
    // }

    // private Connection getConnection() throws SQLException {
    //     // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
    //     Connection con = DataSourceUtils.getConnection(dataSource);
    //     log.info("get connection={}, class={}", con, con.getClass());
    //     return con;
    // }
}
