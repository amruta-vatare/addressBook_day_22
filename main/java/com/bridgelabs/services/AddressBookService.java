package com.bridgelabs.services;

import com.bridgelabs.models.AddressBook;
import com.bridgelabs.repository.AddressBookRepository;
import java.util.List;

public class AddressBookService implements IAddressBookService {
    AddressBookRepository addressBookRepository;
    public AddressBookService(AddressBookRepository addressBookRepository){
        this.addressBookRepository = addressBookRepository;
    }

    @Override
    public void add(AddressBook addressBook) {
        addressBookRepository.add(addressBook);
    }
    @Override
    public boolean delete(String name) {
        for (AddressBook book: addressBookRepository) {
            if (book.getName().equals(name)){
                addressBookRepository.remove(book);
                return true;
            }
        }
        return false;
    }
    @Override
    public List<AddressBook> getAll()
    {
        return addressBookRepository;
    }
}