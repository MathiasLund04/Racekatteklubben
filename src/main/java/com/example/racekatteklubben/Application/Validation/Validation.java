package com.example.racekatteklubben.Application.Validation;

import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.ICatRepository;
import com.example.racekatteklubben.Domain.IMemberRepository;
import com.example.racekatteklubben.Domain.Member;
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
        if (cat.getName() == null || cat.getName().equals("")) {
            throw new ValidationException("Kat skal have navn");
        }
        if (cat.getAge() <= 0){
            throw new ValidationException("Kat skal have gyldig alder");
        }
        if (cat.getBreed() == null || cat.getBreed().equals("")) {
            throw new ValidationException("Katterace skal angives");
        }
        if (cat.getOwner().equals("")) {
            throw new ValidationException("Ejer skal angives");
        }
        if (cat.getEmsCode().equals("")) {
            throw new ValidationException("EMSKode skal angives");
        }
    }

    public void validateMember(Member member) throws ValidationException {
        //WIP

        if (member.getName() == null || member.getName().equals("")) {
            throw new ValidationException("Medlem skal have navn");
        }
        if (member.getEmail() == null || member.getEmail().equals("")) {
            throw new ValidationException("Medlem skal have email");
        }
        if (!member.getEmail().contains("@")){
            throw new ValidationException("Email skal indeholde '@'");
        }
        if (member.getPasswordHash() == null || member.getPasswordHash().equals("")) {
            throw new ValidationException("Medlem skal have kodeord");
        }

    }

}
