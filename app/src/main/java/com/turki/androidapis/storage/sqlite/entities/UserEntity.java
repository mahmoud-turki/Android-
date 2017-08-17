package com.turki.androidapis.storage.sqlite.entities;


import java.io.Serializable;

/**
 * POJO object for user class
 *
 * @author Turki Mahmoud
 */
public class UserEntity implements Serializable {
    private String Name;
    private int id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
