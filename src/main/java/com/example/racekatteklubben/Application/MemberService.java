package com.example.racekatteklubben.Application;

import com.example.racekatteklubben.Application.Validation.Validation;
import com.example.racekatteklubben.Application.Validation.ValidationException;
import com.example.racekatteklubben.Domain.IMemberRepository;
import com.example.racekatteklubben.Domain.Member;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private IMemberRepository mRepo;
    private Validation validation;

    public MemberService(IMemberRepository mRepo, Validation validation) {
        this.mRepo = mRepo;
        this.validation = validation;
    }

    public Member login(String email, String password) throws ValidationException {
        //WIP
        email = email.trim();
        password = password.trim();

        Member member = mRepo.getMemberbyEmail(email);

        validation.validateLogin(member,password);
        return member;
    }

    public void registerMember(Member member) throws ValidationException {
        //WIP
        // Valider input
        validation.validateNewMember(member);


        // Hash password
        hashPassword(member);

        member.setEmail(member.getEmail().trim());
        member.setPassword(member.getPassword().trim());
        // gem i database
        mRepo.addMember(member);
        System.out.println("RAW password: " + member.getPassword());
    }

    public Member getMemberByEmail(String email) throws ValidationException {
        //WIP
        return mRepo.getMemberbyEmail(email);
    }

    public List<Member> getMembers() {
        //WIP
        return mRepo.getAllMembers();
    }

    public void updateMember(Member member, boolean updPass) throws ValidationException {
        //WIP
        if (updPass) {
            hashPassword(member);
        }
        mRepo.updateMember(member);
    }

    public void deleteMember(Member member) throws ValidationException {
        //WIP
        validation.validateString(member.getEmail());
        mRepo.deleteMember(member);
    }

    public void validateMember(Member member) throws ValidationException {
        //WIP
        validation.validateMember(member);
    }

    private void hashPassword(Member member) throws ValidationException {
        String hashed = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
        member.setPasswordHash(hashed);
    }
}
