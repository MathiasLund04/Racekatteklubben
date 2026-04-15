package com.example.racekatteklubben.Domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Cat {
    private int id;
    private String name;
    private String breed;
    private Gender gender;
    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private boolean isDead;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfDeath;

    private String father;
    private String mother;
    private String breeder;
    private Member owner;
    private String color;

    public Cat(){}

    public Cat(String name, String breed, Gender gender, int age, LocalDate dateOfBirth, boolean isDead, LocalDate dateOfDeath, String father, String mother, String breeder, Member owner, String color) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.isDead = isDead;
        this.dateOfDeath = dateOfDeath;
        this.father = father;
        this.mother = mother;
        this.breeder = breeder;
        this.owner = owner;
        this.color = color;
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

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isDead() {
        return isDead;
    }
    public void setDead(boolean dead) {
        isDead = dead;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }
    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String status(){
        if (isDead){
            return "Død";
        } else{
            return "I live";
        }
    }
}
