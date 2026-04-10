package com.example.racekatteklubben.Domain;

import java.util.List;

public interface IMemberRepository {
    List<Member> getAllMembers();

    Member getMemberbyEmail(String Email);

    void addMember(Member member);

    void updateMember(Member member);
}
