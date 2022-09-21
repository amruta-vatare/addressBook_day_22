package com.bridgelabs.manager;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;

public interface IContactManager {
    int chooseOptions();
    void invokeOption(int option);
    void displayOptions();
    void add() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
    void update();
    void delete();
    void display();
}