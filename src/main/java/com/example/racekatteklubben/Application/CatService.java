package com.example.racekatteklubben.Application;

import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.Member;
import com.example.racekatteklubben.Domain.ICatRepository;

import java.util.List;

public class CatService {
    private ICatRepository cRepo;

    public CatService(ICatRepository cRepo) {
        this.cRepo = cRepo;
    }

    public void addCat(Cat cat){
        //WIP
    }

    public void updateCat(Cat cat){
        //WIP
    }

    public void removeCat(int id){
        //WIP
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
