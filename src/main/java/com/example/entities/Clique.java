package com.example.entities;

import javax.persistence.*;

/**
 * Created by ericweidman on 11/1/16.
 */

@Entity
@Table(name = "cliques")
public class Clique {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    private String cliqueName;

    @Column(nullable = false)
    private String message;

    public Clique() {
    }

    public Clique(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public Clique(int id, String cliqueName, String message) {
        this.id = id;
        this.cliqueName = cliqueName;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCliqueName() {
        return cliqueName;
    }

    public void setCliqueName(String cliqueName) {
        this.cliqueName = cliqueName;
    }
}
