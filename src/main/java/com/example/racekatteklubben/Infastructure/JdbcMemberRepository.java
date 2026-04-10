package com.example.racekatteklubben.Infastructure;

import com.example.racekatteklubben.Domain.IMemberRepository;
import com.example.racekatteklubben.Domain.Member;
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
    public Member getMemberbyEmail(String Email) {
        return null;
    }

    @Override
    public void addMember(Member member) {
    }
    @Override
    public void updateMember(Member member) {
    }
}
