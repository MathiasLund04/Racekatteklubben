package com.example.racekatteklubben.Infastructure;

import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.ICatRepository;
import com.example.racekatteklubben.Domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCatRepository implements ICatRepository {

    public Cat getCatById(int id){
        return null;
    }
    public List<Cat> getCats(){
        return null;
    }
    public List<Cat> getCatsByOwner(Member owner){
        return null;
    }
    public List<Cat> getCatsByBreed(String breed){
        return null;
    }

    public List<Cat> getCatsByMother(String mother){
        return null;
    }
    public List<Cat> getCatsByFather(String father){
        return null;
    }
    public void addCat(Cat cat){

    }
    public void updateCat(Cat cat){

    }
    public void removeCat(int id){

    }

}
