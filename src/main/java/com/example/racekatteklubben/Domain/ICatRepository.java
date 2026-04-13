package com.example.racekatteklubben.Domain;

import java.util.List;

public interface ICatRepository {

    List<Cat> getCats();
    Cat getCatById(int id);
    List<Cat> getCatsByOwner(Member owner);
    List<Cat> getCatsByBreed(String breed);
    List<Cat> getCatsByMother(String mother);
    List<Cat> getCatsByFather(String father);
    void addCat(Cat cat);
    void updateCat(int id, Cat cat);
    void removeCat(int id);
}
