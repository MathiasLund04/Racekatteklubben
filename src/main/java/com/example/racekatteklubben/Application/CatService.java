package com.example.racekatteklubben.Application;

import com.example.racekatteklubben.Application.Validation.Validation;
import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.Member;
import com.example.racekatteklubben.Domain.ICatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    private ICatRepository cRepo;
    private Validation validation;

    public CatService(ICatRepository cRepo, Validation validation) {
        this.cRepo = cRepo;
        this.validation = validation;
    }

    public void addCat(Cat cat){
        //WIP
        validation.validateCat(cat);
        cRepo.addCat(cat);
    }

    public void updateCat(int id, Cat cat){
        //WIP
        validation.validateCat(cat);
        cRepo.updateCat(id, cat);

    }

    public void removeCat(int id){
        //WIP
        validation.validateInt(id);
        cRepo.removeCat(id);
    }

    public Cat getCatById(int id){
        //WIP
        validation.validateInt(id);
        return cRepo.getCatById(id);
    }

    public List<Cat> getCatsByOwner(Member owner){
        //WIP
        return cRepo.getCatsByOwner(owner);
    }

    public List<Cat> getCatsByBreed(String breed){
        //WIP
        validation.validateString(breed);
        return cRepo.getCatsByBreed(breed);
    }

    public List<Cat> getCatsByFather(String father){
        //WIP
        validation.validateString(father);
        return cRepo.getCatsByFather(father);
    }

    public List<Cat> getCatsByMother(String mother){
        //WIP
        validation.validateString(mother);
        return cRepo.getCatsByMother(mother);
    }



}
