package com.example.racekatteklubben.Application.Validation;

import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.ICatRepository;
import com.example.racekatteklubben.Domain.IMemberRepository;
import com.example.racekatteklubben.Domain.Member;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    private final ICatRepository cRepo;
    private final IMemberRepository mRepo;

    public Validation(ICatRepository cRepo, IMemberRepository mRepo) {
        this.cRepo = cRepo;
        this.mRepo = mRepo;
    }

    public void validateCat(Cat cat) throws ValidationException {
        if (cat.getName() == null || cat.getName().isEmpty()) {
            throw new ValidationException("Kat skal have navn");
        }
        if (cat.getBreed() == null || cat.getBreed().isEmpty()) {
            throw new ValidationException("Katterace skal angives");
        }
        if (cat.getOwner() == null || cat.getOwner().getEmail() == null || cat.getOwner().getEmail().isEmpty()) {
            throw new ValidationException("Ejer skal angives");
        }
        if (cat.getColor().isEmpty()) {
            throw new ValidationException("Farve skal angives");
        }
    }

    public void validateMember(Member member) throws ValidationException {
        //WIP
        if (member.getName() == null || member.getName().isEmpty()) {
            throw new ValidationException("Medlem skal have navn");
        }
        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new ValidationException("Email skal udfyldes");
        }
        if (!member.getEmail().contains("@")) {
            throw new ValidationException("Email skal indeholde '@'");
        }
        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            throw new ValidationException("Password skal udfyldes");
        }
    }

    public void validateNewMember(Member member){
        validateMember(member);

        Member existing = mRepo.getMemberbyEmail(member.getEmail());
        if (existing != null) {
            throw new ValidationException("Email er allerede registreret");
        }
    }

    public void validateLogin(Member member, String password) throws ValidationException {
        if(member == null || !BCrypt.checkpw(password, member.getPasswordHash()) ) {
            throw new ValidationException("Bruger eller password forkert");
        }
    }
    public void validateString(String word) throws ValidationException {
        if (word == null || word.isEmpty()){
            throw new ValidationException("Venligst udfyld dette");
        }
    }
    public void validateInt(int number) throws ValidationException {
        if (number <= 0){
            throw new ValidationException("Venligst indtast et tal over 0");
        }
    }
}
