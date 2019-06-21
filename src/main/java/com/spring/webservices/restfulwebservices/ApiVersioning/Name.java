package com.spring.webservices.restfulwebservices.ApiVersioning;

public class Name {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setSecondName(String secondName) {
        this.lastName = secondName;
    }

    public Name(String firstName, String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
    }
}
