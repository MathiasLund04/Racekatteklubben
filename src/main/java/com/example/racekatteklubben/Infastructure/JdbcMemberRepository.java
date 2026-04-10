package com.example.racekatteklubben.Infastructure;

import com.example.racekatteklubben.Domain.IMemberRepository;
import com.example.racekatteklubben.Domain.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMemberRepository implements IMemberRepository {

    private final JdbcTemplate jdbcTemp;

    public JdbcMemberRepository(JdbcTemplate jdbcTemp) {
        this.jdbcTemp = jdbcTemp;
    }

    @Override
    public List<Member> getAllMembers() {
        return List.of();
    }

    @Override
    public Member getMemberbyEmail(String email) {
        String sql = """
                SELECT
                    Name,
                    Email,
                    PasswordHash
                FROM member
                WHERE Email = ?
                """;

        try{
            return jdbcTemp.queryForObject(sql,
                    (rs, rowNum) -> new Member(
                            rs.getString("Name"),
                            rs.getString("Email"),
                            rs.getString("PasswordHash")
            ),
            email
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void addMember(Member member) {
        String sql = """
                INSERT INTO member 
                (Name, Email, PasswordHash) 
                VALUES (?, ?, ?) 
                """;

        jdbcTemp.update(sql,
                member.getName(),
                member.getEmail(),
                member.getPasswordHash()
        );
    }

    @Override
    public void updateMember(Member member) {
        String sql = """
                UPDATE member
                SET 
                    Name = ?,
                    PasswordHash = ?
                WHERE Email = ? 
                """;
        jdbcTemp.update(sql, member.getName(), member.getPasswordHash(), member.getEmail());
    }

    @Override
    public void deleteMember(Member member) {
        String sql = """
                DELETE FROM member
                where Email = ?
                """;
        jdbcTemp.update(sql, member.getEmail());
    }
}
