package com.bridgelabs.manager;

import com.bridgelabs.models.Person;

import java.util.List;

public interface IAddressBookManager {
    int chooseOptions();
    void invokeOption(int option);
    void displayOptions();
    void add();
    void display();
    void delete();
    void getContactsByCity();
    void getContactsByState();
}
