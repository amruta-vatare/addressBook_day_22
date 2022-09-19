package com.bridgelabs.manager;

import com.bridgelabs.models.Person;
import com.bridgelabs.services.IContactService;

import java.util.List;
import java.util.Scanner;

public class ContactManager implements IContactManager {
    IContactService contactService;
    public ContactManager(IContactService contactService) {
        this.contactService = contactService;
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
                update();
                break;
            case 3:
                delete();
                break;
            case 4:
                display();
                break;
            case 5:
                System.out.println("Enter city name");
                Scanner sc = new Scanner(System.in);
                getContactByCity(sc.next());
                break;
            default:
                break;
        }
    }
    @Override
    public void displayOptions() {
        System.out.println("1. Add Contact");
        System.out.println("2. Edit Contact");
        System.out.println("3. Delete Contact");
        System.out.println("4. Display Contact");
        System.out.println("5. Display Contact by city");
        System.out.println("Press 0 to exit");
    }
    @Override
    public void add(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many contacts you want to add");
        int count = scanner.nextInt();
        for(int i = 1;i<=count;i++){
            System.out.println("Enter contact "+i+" details");
            Person person =  getContactDetails();
            contactService.add(person);
            System.out.println("Added successfully");
        }
    }
    @Override
    public void update(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name which you want to update contact");
        String firstName = scanner.next();
        Person updatedContact = getContactDetails();
        contactService.edit(firstName,updatedContact);
        System.out.println("Updated successfully");

    }
    @Override
    public void delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name which you want to delete contact");
        String dFirstName = scanner.next();
        contactService.delete(dFirstName);
        System.out.println("Deleted successfully");
    }
    @Override
    public void display(){
        System.out.println("All Contacts");
        for (Person person: contactService.getAll()) {
            System.out.println(person);
        }
    }
    private Person getContactDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first name");
        String fName = scanner.next();
        System.out.println("Enter last name");
        String lName = scanner.next();
        System.out.println("Enter address name");
        String address = scanner.next();
        System.out.println("Enter city name");
        String city = scanner.next();
        System.out.println("Enter state name");
        String state = scanner.next();
        System.out.println("Enter zip code name");
        String zipCode = scanner.next();
        System.out.println("Enter phone number name");
        String phoneNo = scanner.next();
        System.out.println("Enter email name");
        String email = scanner.next();
        Person p = new Person(fName,lName,address,city,state,zipCode,phoneNo,email);
        return p;
    }

    public void getContactByCity(String city){
        List<Person> personListByCity = contactService.getContactByCity(city);
        for (Person p :personListByCity) {
            System.out.println(p.toString());
        }
    }
}
