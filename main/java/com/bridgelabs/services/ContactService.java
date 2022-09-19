package com.bridgelabs.services;

import com.bridgelabs.models.Person;
import com.bridgelabs.repository.ContactRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactService implements IContactService {
    ContactRepository contactRepository;
    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public void add(Person person) {
        contactRepository.add(person);
    }

    @Override
    public Person get(String firstName) {
        for (Person person: contactRepository) {
            if(person.getFirstName().equals(firstName)){
                return person;
            }
        }
        return null;
    }

    @Override
    public boolean delete(String firstName) {
        for (Person person: contactRepository) {
            if(person.getFirstName().equals(firstName)){
                contactRepository.remove(person);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean edit(String firstName, Person person) {
        Person existingPerson = get(firstName);
        if (existingPerson == null) {
            return false;
        }
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setAddress(person.getAddress());
        existingPerson.setCity(person.getCity());
        existingPerson.setZipCode(person.getZipCode());
        existingPerson.setState(person.getState());
        existingPerson.setPhoneNumber(person.getPhoneNumber());
        existingPerson.setEmailId(person.getEmailId());
        return true;
    }

    @Override
    public List<Person> getAll() {
        return contactRepository;
    }

    @Override
    public Map<String, List<Person>> getContactByCity(){
        Map<String, List<Person>> contactsByCity=
                contactRepository.stream().collect(Collectors.groupingBy(Person::getCity));
        return contactsByCity;
    }

    @Override
    public Map<String, Long> getContactsByState() {
        Map<String, Long> cityAndNoOfContacts =
                contactRepository.stream().collect(Collectors.groupingBy(Person::getState, Collectors.counting()));
        return cityAndNoOfContacts;
    }
}
