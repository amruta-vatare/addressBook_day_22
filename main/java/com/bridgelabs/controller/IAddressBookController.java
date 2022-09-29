package com.bridgelabs.controller;

public interface IAddressBookController {
    int chooseOptions();
    void invokeOption(int option);
    void displayOptions();
    void add();
    void display();
    void delete();
    void getContactsByCity();
    void getContactsByState();
}
