package com.bridgelabs.services;

import com.bridgelabs.models.Person;
import com.bridgelabs.repository.ContactRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactService implements IContactService {
    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public int add(String addressBookName, Person person) {

        return contactRepository.add(addressBookName, person);
    }

    @Override
    public List<Person> getAll() {
        List<Person> contacts = contactRepository.getAll();
        return contacts;
    }

    @Override
    public boolean update(String email_id, Person person) {
        int affected = contactRepository.update(email_id, person);
        if (affected != 0)
            return true;

        return false;
    }

    @Override
    public boolean delete(String email_id) {
        int affectedRow = contactRepository.delete(email_id);
        if (affectedRow != 0)
            return true;
        return false;
    }

    //UC8 Ability to retrieve entries sorted alphabetically by Person’s name for a given city
    @Override
    public List<Person> getContactByCity(String city) {
        List<Person> contactsByCity = contactRepository.getContactByCity(city);
        return contactsByCity;
    }

    //UC6 Ability to Retrieve Person belonging to a City or State from the Address Book
    @Override
    public List<Person> getContactsByState(String state) {
        List<Person> contactsByState = contactRepository.getContactByState(state);
        return contactsByState;
    }

    //UC7 Ability to understand the size of address book by State
    @Override
    public Map<String, Long> getContactsCountByState(String state) {
        List<Person> cityAndNoOfContacts = contactRepository.getContactByState(state);
        Map<String, Long> mapStateCount = cityAndNoOfContacts.stream().collect(Collectors.groupingBy(Person::getState, Collectors.counting()));
        return mapStateCount;
    }
}
