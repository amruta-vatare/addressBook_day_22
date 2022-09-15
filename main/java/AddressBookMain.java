import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {

    private List<Person> contact = new ArrayList<>();
    private AddressBook book = new AddressBook();

    public List<Person> createContact(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name");
        String fName = sc.next();
        System.out.println("Enter last name");
        String lName = sc.next();
        System.out.println("Enter address name");
        String address = sc.next();
        System.out.println("Enter city name");
        String city = sc.next();
        System.out.println("Enter state name");
        String state = sc.next();
        System.out.println("Enter zip code name");
        String zipCode = sc.next();
        System.out.println("Enter phone number name");
        String phoneNo = sc.next();
        System.out.println("Enter email name");
        String email = sc.next();
        Person p = new Person(fName,lName,address,city,state,zipCode,phoneNo,email);
        contact.add(p);
        return contact;
    }
    public static void main(String[] args) {
        AddressBookMain main = new AddressBookMain();
        System.out.println("Welcome to address book program");
        List<Person> personList = main.createContact();
        main.addToAddressBook(personList);
        main.display();
    }

    private void addToAddressBook(List<Person> personList) {
        book.setContactList(contact);
    }

    private void display() {
        List<Person> contact = book.getContactList();
        for (Person p:contact) {System.out.println(p);}
    }

}
