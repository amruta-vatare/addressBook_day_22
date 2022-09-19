package com.bridgelabs.models;

import java.util.ArrayList;
import java.util.LinkedList;

public class AddressBook {
    String name;
    ArrayList<Person> contactList;

    public AddressBook() {

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Person> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Person> contactList) {
        this.contactList = contactList;
    }
}