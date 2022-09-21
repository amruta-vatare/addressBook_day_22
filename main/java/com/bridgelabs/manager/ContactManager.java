package com.bridgelabs.manager;

import com.bridgelabs.models.Person;
import com.bridgelabs.services.IContactService;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.COMMA;

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
                getContactByCity();
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
    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many contacts you want to add");
        int count = scanner.nextInt();
        for(int i = 1;i<=count;i++){
            System.out.println("Enter contact "+i+" details");
            Person person =  getContactDetails();
            List<Person> personsContact;
            //UC14 Write
            try{
                FileWriter writer = new FileWriter("C:\\Users\\Amruta\\BridgeLabsProjects\\Persons.csv");
                // Creating StatefulBeanToCsv object
                StatefulBeanToCsvBuilder<Person> builder= new StatefulBeanToCsvBuilder<>(writer);
                StatefulBeanToCsv beanWriter = builder.build();
                contactService.add(person);
                personsContact = contactService.getAll();
                // Write list to StatefulBeanToCsv object
                beanWriter.write(personsContact);

                // closing the writer object
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
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
        /*System.out.println("All Contacts");
        for (Person person: contactService.getAll()) {
            System.out.println(person);
        }*/
        System.out.println("DisplayFrom CSV");
        List<Person> personByCSVFile = processInputFile();
        for (Person p:personByCSVFile) {
            System.out.println(p);
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
    //UC9
    public void getContactByCity(){
        Map<String, List<Person>> personListByCity = contactService.getContactByCity();
        Set<Map.Entry<String, List<Person>>> entrySet = personListByCity.entrySet();
        for (Map.Entry<String, List<Person>> entry : entrySet)
        {
            System.out.println("--------------------------------------");

            System.out.println("Contacts In "+entry.getKey() + " : ");

            List<Person> list = entry.getValue();

            for (Person person : list)
            {
                System.out.println(person.getFirstName()+" "+person.getLastName() );
            }
            System.out.println("--------------------------------------");
        }
    }
    //UC10
    public void getContactsByState(){
        Map<String, Long> ContactsByState = contactService.getContactsByState();
        Set<Map.Entry<String, Long>> entrySet = ContactsByState.entrySet();
        for (Map.Entry<String, Long> entry : entrySet)
        {
            System.out.println("--------------------------------------");

            System.out.println("Contacts In "+entry.getKey() + " : "+entry.getValue());

            System.out.println("--------------------------------------");
        }
    }
    //UC13 UC14Read
    public List<Person> processInputFile(){
        List<Person> inputList = new ArrayList<Person>();
        try{
            File inputF = new File("C:\\Users\\Amruta\\BridgeLabsProjects\\Persons.csv");
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return inputList ;
    }
    private Function<String, Person> mapToItem = (line) -> {
        String[] p = line.split("[,]");// a CSV has comma separated lines
        Person item = new Person();
        item.setFirstName(p[0]);//<-- this is the first column in the csv file
        item.setLastName(p[1]);
        item.setAddress(p[2]);
        item.setCity(p[3]);
        item.setState(p[4]);
        item.setZipCode(p[5]);
        item.setPhoneNumber(p[6]);
        item.setEmailId(p[7]);
        //more initialization goes here
        return item;
    };
}
