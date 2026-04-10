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

    public void removeCat(int id, Cat cat){
        //WIP
        cRepo.removeCat(id, cat);
    }

    public Cat getCatById(int id){
        //WIP
        return null;
    }

    public List<Cat> getCatsByOwner(Member owner){
        //WIP
        return null;
    }

    public List<Cat> getCatsByBreed(String breed){
        //WIP
        return null;
    }

    public List<Cat> getCatsByFather(String father){
        //WIP
        return null;
    }

    public List<Cat> getCatsByMother(String mother){
        //WIP
        return null;
    }

}
