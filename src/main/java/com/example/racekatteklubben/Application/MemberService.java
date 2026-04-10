package com.example.racekatteklubben.Application;

import com.example.racekatteklubben.Domain.IMemberRepository;
import com.example.racekatteklubben.Domain.Member;

import java.util.List;

public class MemberService {
    private IMemberRepository mRepo;

    public MemberService(IMemberRepository mRepo) {this.mRepo = mRepo;}

    public void login(String email, String password) {
        //WIP
    }

    public void registerMember(Member member) {
        //WIP
    }

    public Member getMember(String email) {
        //WIP
        return null;
    }

    public List<Member> getMembers() {
        //WIP
        return null;
    }

    public void updateMember(Member member) {
        //WIP
    }

    public void deleteMember(Member member) {
        //WIP
    }

    public void validateMember(Member member) {
        //WIP
    }
}
