package com.bridgelabs.services;

import com.bridgelabs.models.Person;

import java.util.List;

public interface IContactService {
    void add(Person person);
    Person get(String name);
    boolean delete(String name);
    boolean edit(String name, Person person);
    List<Person> getAll();
    List<Person> getContactByCity(String name);
}
