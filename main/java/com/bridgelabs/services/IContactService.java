package com.bridgelabs.services;

import com.bridgelabs.models.Person;

import java.util.List;
import java.util.Map;

public interface IContactService {
    int add(String addressBookName, Person person);

    List<Person> getAll();

    boolean update(String email_id, Person person);

    boolean delete(String name);

    List<Person> getContactByCity(String city);

    List<Person> getContactsByState(String state);

    Map<String, Long> getContactsCountByState(String state);

}
