package com.bridgelabs.services;

import com.bridgelabs.models.AddressBook;
import com.bridgelabs.models.AddressBookType;
import com.bridgelabs.repository.AddressBookRepository;
import java.util.List;

public class AddressBookService implements IAddressBookService {
    AddressBookRepository addressBookRepository;
    public AddressBookService(AddressBookRepository addressBookRepository){
        this.addressBookRepository = addressBookRepository;
    }

    @Override
    public boolean add(AddressBook addressBook) {
        int affectedRows = addressBookRepository.add(addressBook);
        if(affectedRows != 0)
            return true;
        return false;
    }
    @Override
    public boolean delete(String name) {
        int affectedRows = addressBookRepository.delete(name);
        if(affectedRows != 0)
            return true;
        return false;
    }
    @Override
    public List<AddressBook> getAll()
    {
        return addressBookRepository.getAll();
    }

    @Override
    public List<AddressBookType> getTypes() {
        return addressBookRepository.getTypes();
    }
}