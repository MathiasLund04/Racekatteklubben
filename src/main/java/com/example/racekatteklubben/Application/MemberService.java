package com.example.racekatteklubben.Application;

import com.example.racekatteklubben.Application.Validation.Validation;
import com.example.racekatteklubben.Application.Validation.ValidationException;
import com.example.racekatteklubben.Domain.IMemberRepository;
import com.example.racekatteklubben.Domain.Member;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

    public void login(String email, String password) {
        //WIP
        if (email.isEmpty() || password.isEmpty()) {
            throw new ValidationException("Email og password skal udfyldes");
        }

        Member member = mRepo.getMemberbyEmail(email);

        if(member == null) {
            throw new  ValidationException("Bruger findes ikke");
        }

        if (!BCrypt.checkpw(password, member.getPasswordHash())) {
            throw new ValidationException("Forkert password");
        }
    }

    public void registerMember(Member member) {
        //WIP
        // Valider input
        validation.validateMember(member);

        // Tjek om email allerede findes
        Member existing = mRepo.getMemberbyEmail(member.getEmail());
        if (existing != null) {
            throw new ValidationException("Email er allerede registreret");
        }

        // Hash password
        String hashed = BCrypt.hashpw(member.getPasswordHash(), BCrypt.gensalt());
        member.setPasswordHash(hashed);

        // gem i database
        mRepo.addMember(member);
    }

    public Member getMember(String email) {
        //WIP
        if (email.isEmpty()) {
            throw new ValidationException("Email skal udfyldes");
        }
        return mRepo.getMemberbyEmail(email);
    }

    public List<Member> getMembers() {
        //WIP
        return null;
    }

    public void updateMember(Member member) {
        //WIP
        validation.validateMember(member);
        mRepo.updateMember(member);
    }

    public void deleteMember(Member member) {
        //WIP
        mRepo.deleteMember(member);
    }

    public void validateMember(Member member) {
        //WIP
        validation.validateMember(member);
    }
}
