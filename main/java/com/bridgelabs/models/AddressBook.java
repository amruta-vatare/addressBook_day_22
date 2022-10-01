package com.bridgelabs.models;

public class AddressBook {
    int address_book_id;
    String name;
    int type;


    public AddressBook() {

    }

    public int getAddress_book_id() {
        return address_book_id;
    }

    public void setAddress_book_id(int address_book_id) {
        this.address_book_id = address_book_id;
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