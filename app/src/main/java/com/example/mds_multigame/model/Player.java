package com.example.mds_multigame.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player {

    @NonNull
    @PrimaryKey
    private String name;
    private String picture;
    private String firstname;
    private int age;
    private String localisation;

    public Player(String picture, String name, String firstname, int age, String localisation){
        setName(name);
        setFirstname(firstname);
        setAge(age);
        setPicture(picture);
        setLocalisation(localisation);
    }
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}
