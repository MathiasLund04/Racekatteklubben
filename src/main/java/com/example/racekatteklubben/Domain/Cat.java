package com.example.racekatteklubben.Domain;

public class Cat {
    private int id;
    private String name;
    private String breed;
    private int age;
    private String dad;
    private String mom;
    private String breeder;
    private Member owner;
    private String emsCode;

    public Cat(){}

    public Cat(String name, String breed, int age, String dad, String mom, String breeder, Member owner, String emsCode) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.dad = dad;
        this.mom = mom;
        this.breeder = breeder;
        this.owner = owner;
        this.emsCode = emsCode;
    }

    public int getId() {
        return id;
    }

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

    public String getDad() {
        return dad;
    }
    public void setDad(String dad) {
        this.dad = dad;
    }

    public String getMom() {
        return mom;
    }
    public void setMom(String mom) {
        this.mom = mom;
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
