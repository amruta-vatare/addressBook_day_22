package com.bridgelabs.models;
public class AddressBook {
    String name;
    int type;


    public AddressBook() {

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}