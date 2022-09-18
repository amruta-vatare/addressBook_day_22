import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
   static AddressBookRepository addressBookRepository = new AddressBookRepository();
    static AddressBookService addressBookService = new AddressBookService(addressBookRepository);
    public static void main(String[] args) {
        AddressBookManager addressBookManager = new AddressBookManager(addressBookService);
        int selectedOption;
        do {
            addressBookManager.displayOptions();
            selectedOption = addressBookManager.chooseOptions();
            addressBookManager.invokeOption(selectedOption);
        }while(selectedOption !=0);
        System.out.println("Thank you visiting");
    }
}
