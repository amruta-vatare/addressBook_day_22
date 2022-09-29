package com.bridgelabs.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;

public interface IContactController {
    int chooseOptions();

    void invokeOption(int option);

    void displayOptions();

    void display();

    void update();

    void add() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;

    void delete();
}