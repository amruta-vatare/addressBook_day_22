package com.bridgelabs.services;

import com.bridgelabs.models.AddressBook;
import com.bridgelabs.models.AddressBookType;

import java.util.List;

public interface IAddressBookService {
    boolean add(AddressBook addressBook);
    boolean delete(String name);
    List<AddressBook> getAll();

    List<AddressBookType> getTypes();
}
