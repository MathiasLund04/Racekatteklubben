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
        if (cat.getName() == null || cat.getName().isEmpty()) {
            throw new ValidationException("Kat skal have navn");
        }
        if (cat.getAge() <= 0){
            throw new ValidationException("Kat skal have gyldig alder");
        }
        if (cat.getBreed() == null || cat.getBreed().isEmpty()) {
            throw new ValidationException("Katterace skal angives");
        }
        if (cat.getOwner().getName().isEmpty()) {
            throw new ValidationException("Ejer skal angives");
        }
        if (cat.getEmsCode().isEmpty()) {
            throw new ValidationException("EMSKode skal angives");
        }
    }

    public void validateMember(Member member) throws ValidationException {
        //WIP

        if (member.getName() == null || member.getName().isEmpty()) {
            throw new ValidationException("Medlem skal have navn");
        }
        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new ValidationException("Medlem skal have email");
        }
        if (!member.getEmail().contains("@")){
            throw new ValidationException("Email skal indeholde '@'");
        }
        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            throw new ValidationException("Medlem skal have kodeord");
        }

    }

    public void validateString(String word) throws ValidationException {
        if (word.isEmpty()){
            throw new ValidationException("Venligst udfyld dette");
        }
    }

    public void validateInt(int number) throws ValidationException {
        if (number <= 0){
            throw new ValidationException("Venligst indtast et tal over 0");
        }
    }

}
