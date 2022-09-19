package com.bridgelabs.manager;

public interface IContactManager {
    int chooseOptions();
    void invokeOption(int option);
    void displayOptions();
    void add();
    void update();
    void delete();
    void display();
}