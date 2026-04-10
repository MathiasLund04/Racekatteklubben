package com.example.racekatteklubben.Domain;

public class Cat {
    private int id;
    private String name;
    private String breed;
    private int age;
    private String father;
    private String mother;
    private String breeder;
    private Member owner;
    private String emsCode;

    public Cat(){}

    public Cat(String name, String breed, int age, String father, String mother, String breeder, Member owner, String emsCode) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.father = father;
        this.mother = mother;
        this.breeder = breeder;
        this.owner = owner;
        this.emsCode = emsCode;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getFather() {
        return father;
    }
    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }
    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getBreeder() {
        return breeder;
    }
    public void setBreeder(String breeder) {
        this.breeder = breeder;
    }

    public Member getOwner() {
        return owner;
    }
    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public String getEmsCode() {
        return emsCode;
    }
    public void setEmsCode(String emsCode) {
        this.emsCode = emsCode;
    }
}
