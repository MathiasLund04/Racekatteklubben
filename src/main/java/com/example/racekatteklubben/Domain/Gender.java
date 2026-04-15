package com.example.racekatteklubben.Domain;

public enum Gender {
    FEMALE("Hunkøn"),
    MALE("Hankøn");

    private String description;
    Gender(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }


}
