package com.bridgelabs.controller;

import com.bridgelabs.models.AddressBook;
import com.bridgelabs.models.AddressBookType;
import com.bridgelabs.repository.ContactRepository;
import com.bridgelabs.services.ContactService;
import com.bridgelabs.services.IAddressBookService;
import com.bridgelabs.services.IContactService;

import java.util.List;
import java.util.Scanner;

public class AddressBookController implements IAddressBookController {
    IAddressBookService addressBookService;
    public AddressBookController(IAddressBookService addressBookService){
        this.addressBookService = addressBookService;
    }

    @Override
    public int chooseOptions() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the your choice");
        int choice = sc.nextInt();
        return choice;
    }

    @Override
    public void invokeOption(int option) {
        switch (option) {
            case 1:
                add();
                break;
            case 2:
                delete();
                break;
            case 3:
                display();
                break;
            case 4:
                getContactsByCity();
                break;
            case 5:
                getContactsByState();
                break;
            default:
                break;
        }
    }

    @Override
    public void displayOptions() {
        System.out.println("1. Add Address Book");
        System.out.println("2. Delete Address Book");
        System.out.println("3. Display Address Books");
        System.out.println("4. Search Contacts By City");
        System.out.println("5. No of Contacts in state");
        System.out.println("Press 0 to exit");
    }
    public void chooseType(){
        List<AddressBookType> types = addressBookService.getTypes();
        for (int i = 0;i<types.size();i++){
            System.out.println(i+1+" "+types.get(i).getType_name());
        }
    }

    @Override
    public void add() {
        AddressBook addressBook = new AddressBook();
        ContactRepository contactRepository = new ContactRepository();
        IContactService contactService = new ContactService(contactRepository);
        ContactController contactController = new ContactController(contactService);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter address book name");
        String addressBookName = scanner.next();
        addressBook.setName(addressBookName);
        System.out.println("Choose Type");
        chooseType();
        int type = scanner.nextInt();
        addressBook.setType(type);
        if(addressBookService.add(addressBook)){
            System.out.println("Address book " + addressBook.getName() + " added successfully");
            System.out.println("Please choose option below to manage address book '" + addressBook.getName() + "'");
            int selectedOption;
            do {
                contactController.displayOptions();
                selectedOption = contactController.chooseOptions();
                contactController.invokeOption(selectedOption);
            }while(selectedOption !=0);
            System.out.println("Successfully exited the address book " + addressBook.getName());
        }
    }

    @Override
    public void display() {
        System.out.println("All Address books");
        for (AddressBook addressBook: addressBookService.getAll()) {
            System.out.println("Address book " + addressBook.getName());
            System.out.println("----------------------------------------");
        }
    }

    @Override
    public void delete() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Enter address book name to delete");
        String name = scanner.next();
        if(addressBookService.delete(name))
            System.out.println("Deleted Successfully");
    }

    @Override
    public void getContactsByCity() {
        Scanner scanner = new Scanner(System.in);
        for (AddressBook addressBook: addressBookService.getAll()) {
            System.out.println("----------------------------------------");
            System.out.println("Address book " + addressBook.getName());
            ContactRepository contactRepository = new ContactRepository();
            /*contactRepository.addAll(addressBook.getContactList());
            IContactService contactService = new ContactService(contactRepository);
            ContactController contactManager = new ContactController(contactService);
            contactManager.getContactByCity();*/
        }
    }

    @Override
    public void getContactsByState() {
        Scanner scanner = new Scanner(System.in);
        for (AddressBook addressBook: addressBookService.getAll()) {
            System.out.println("----------------------------------------");
            System.out.println("Address book " + addressBook.getName());
            /*ContactRepository contactRepository = new ContactRepository();
            contactRepository.addAll(addressBook.getContactList());
            IContactService contactService = new ContactService(contactRepository);
            ContactController contactManager = new ContactController(contactService);
            contactManager.getContactsByState();*/
        }
    }

}
