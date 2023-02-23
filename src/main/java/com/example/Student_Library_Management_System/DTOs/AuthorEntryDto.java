package com.example.Student_Library_Management_System.DTOs;

// this is just an object that will be used for take the request from the postman
// it will contain only those parameters which we are taking from postman
public class AuthorEntryDto {

    // id will not come here as we are auto generating it and don't want user to mess up with the id
    private String name;

    private int age;

    private String country;

    private double rating;

    public AuthorEntryDto() {
    }

    public AuthorEntryDto(String name, int age, String country, double rating) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
