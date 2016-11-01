package com.example.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by ericweidman on 10/31/16.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;


    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String imgLoc;



    public User() {
    }

    public User(String userName, String email, String password, String firstName, String lastName, String imgLoc) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imgLoc = imgLoc;
    }

    public User(int id) {
        this.id = id;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
