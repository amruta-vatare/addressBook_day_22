package com.bridgelabs.services;

import com.bridgelabs.models.AddressBook;

import java.util.List;

public interface IAddressBookService {
    void add(AddressBook addressBook);
    boolean delete(String name);
    List<AddressBook> getAll();
}
