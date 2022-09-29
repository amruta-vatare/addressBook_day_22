package com.bridgelabs.services;

import com.bridgelabs.models.AddressBookType;
import com.bridgelabs.models.Person;

import java.util.List;

public interface IAddressBookTypeService {
    void add(AddressBookType type);
    Person get(String name);
    boolean delete(String name);
    boolean edit(String name, AddressBookType Type);
    List<AddressBookType> getAll();
}
