package com.example.androiddevjava.model;


import java.io.Serializable;

public class UserLoginModel implements Serializable {
    private Long Id;
    private String Name;

    public UserLoginModel(String name) {
        Name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
