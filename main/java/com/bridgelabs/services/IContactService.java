package com.bridgelabs.services;

import com.bridgelabs.models.Person;

import java.util.List;
import java.util.Map;

public interface IContactService {
    void add(Person person);
    Person get(String name);
    boolean delete(String name);
    boolean edit(String name, Person person);
    List<Person> getAll();
    Map<String, List<Person>> getContactByCity();
    Map<String,Long> getContactsByState();
}
