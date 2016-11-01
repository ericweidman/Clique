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
    private String message;

    public Clique() {
    }

    public Clique(int id, String message) {
        this.id = id;
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
}
