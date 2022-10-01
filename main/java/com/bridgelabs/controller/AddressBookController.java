package com.bridgelabs.controller;

import com.bridgelabs.models.AddressBook;
import com.bridgelabs.models.AddressBookType;
import com.bridgelabs.services.IAddressBookService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AddressBookController implements IAddressBookController {
    IAddressBookService addressBookService;

    public AddressBookController(IAddressBookService addressBookService) {
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
                getAddressBookByNameWithType();
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
        System.out.println("4. Display Address Book by Types");
        System.out.println("Press 0 to exit");
    }

    public void chooseType() {
        List<AddressBookType> types = addressBookService.getTypes();
        for (int i = 0; i < types.size(); i++) {
            System.out.println(types.get(i).getType_id() +" : " + types.get(i).getType_name());
        }
    }

    @Override
    public void add() {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter address book name");
        String addressBookName = scanner.next();
        addressBook.setName(addressBookName);
        System.out.println("Choose Type");
        chooseType();
        int type = scanner.nextInt();
        addressBook.setType(type);
        if (addressBookService.add(addressBook)) {
            System.out.println("Address book " + addressBook.getName() + " added successfully");
            System.out.println("Successfully exited the address book " + addressBook.getName());
        }
    }

    @Override
    public void display() {
        List<AddressBook> books = addressBookService.getAll();
        System.out.println(" \tAddress books ");
        System.out.println("------------------------------------------");
        for (AddressBook addressBook : books) {
            System.out.println(addressBook.getAddress_book_id()+" : " + addressBook.getName());
        }
        System.out.println("------------------------------------------");
        books = null;
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter address book name to delete");
        String name = scanner.next();
        if (addressBookService.delete(name))
            System.out.println("Deleted Successfully");
    }

    //UC9 Ability to identify each Address Book with name and Type.
    private void getAddressBookByNameWithType() {
        Map<AddressBook,String> addressBooks = addressBookService.getAddressBookByNameWithType();
        Set<Map.Entry<AddressBook,String>> entrySet = addressBooks.entrySet();
        for (Map.Entry<AddressBook,String> entry:entrySet) {
            System.out.println("--------------------------------------");
            System.out.println("Address Book : "+entry.getKey().getName()+" is type of "+entry.getValue());
            System.out.println("--------------------------------------");
        }
    }


}
