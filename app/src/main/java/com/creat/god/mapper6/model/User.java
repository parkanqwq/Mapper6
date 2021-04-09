package com.creat.god.mapper6.model;

public class User {

    private int id;
    private String name;
    private String power;

    public User(int id, String name, String power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
