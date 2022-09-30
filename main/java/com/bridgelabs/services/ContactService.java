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
    public int add(String addressBookName,Person person) {

        return contactRepository.add(addressBookName,person);
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
            if(affectedRow != 0)
                return true;
        return false;
    }
    @Override
    public List<Person> getContactByCity(String city){
        List<Person> contactsByCity = contactRepository.getAll();
        contactsByCity.stream().collect(Collectors.groupingBy(Person::getCity));
        return contactsByCity;
    }
    /*
    @Override
    public Map<String, Long> getContactsByState() {
        Map<String, Long> cityAndNoOfContacts =
                contactRepository.stream().collect(Collectors.groupingBy(Person::getState, Collectors.counting()));
        return cityAndNoOfContacts;
    }*/
}
