package com.example.entities;

import javax.persistence.*;

/**
 * Created by ericweidman on 10/31/16.
 */


@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private int id;


    @Column(nullable = false)
    private String message;

    @ManyToOne
    User user;


    public Message() {
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

    public Message(int id, String message){
        this.id = id;
        this.message = message;
    }

    public Message(String message, User user) {
        this.message = message;
        this.user = user;
    }
}
