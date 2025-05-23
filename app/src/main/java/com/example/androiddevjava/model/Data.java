package com.example.androiddevjava.model;

import java.io.Serializable;

public class Data  implements Serializable {
    private String id;
    private String name;
    private String job;

    public Data(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
