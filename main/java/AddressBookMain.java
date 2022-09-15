import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {

    private List<Person> contact = new ArrayList<>();
    private AddressBook book = new AddressBook();

    public Person createContact(){
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
        return p;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to address book program");
        Scanner sc = new Scanner(System.in);
        AddressBookMain main = new AddressBookMain();
        int choice = 1;
        do {
            System.out.println("Enter the your choice");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Display Contact");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    Person p = main.createContact();
                    main.addToContactList(p);
                    main.addToAddressBook(main.contact);
                    break;
                case 2:
                    main.editContact();
                    break;
                case 3:
                    main.deleteContact();
                    break;
                case 4:
                    main.display();
                    break;
                default:
                    break;
            }
        }while (choice!=0);

    }

    private void deleteContact() {
        Scanner sc = new Scanner(System.in);
        display();
        System.out.println("Enter the name of which you want to delete contact");
        String name = sc.next();
        for (Person p :contact) {
            if(p.getFirstName().equals(name)){
                contact.remove(p);
            }
        }

    }

    private void editContact() {
        Scanner sc = new Scanner(System.in);
        display();
        System.out.println("Enter the name of which you want to edit contact");
        String name = sc.next();
        for (Person p :contact) {
            if(p.getFirstName().equals(name)){
                System.out.println("Enter first name");
                p.setFirstName(sc.next());
                System.out.println("Enter last name");
                p.setLastName(sc.next());
                System.out.println("Enter address");
                p.setAddress(sc.next());
                System.out.println("Enter city");
                p.setCity(sc.next());
                System.out.println("Enter state");
                p.setState(sc.next());
                System.out.println("Enter zip code");
                p.setZipCode(sc.next());
                System.out.println("Enter phone no");
                p.setPhoneNumber(sc.next());
                System.out.println("Enter email-id");
                p.setEmailId(sc.next());
            }
        }
    }

    private void addToContactList(Person p){
        Scanner sc = new Scanner(System.in);
        contact.add(p);
        System.out.println("Press 1 to add more contact");
        int add = sc.nextInt();
        if(add == 1){;
        addToContactList(createContact());
        }
    }

    private void addToAddressBook(List<Person> personList) {
        book.setContactList(contact);
    }

    private void display() {
        List<Person> contact = book.getContactList();
        for (Person p:contact) {System.out.println(p);}
    }

}
