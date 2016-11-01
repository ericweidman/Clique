package com.example.entities;

import javax.persistence.*;

/**
 * Created by ericweidman on 10/31/16.
 */


@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    User user;

    private boolean isFriend;

    public Friend() {
    }

    public Friend(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
