import com.bridgelabs.controller.AddressBookController;
import com.bridgelabs.controller.ContactController;
import com.bridgelabs.controller.IAddressBookController;
import com.bridgelabs.controller.IContactController;
import com.bridgelabs.repository.AddressBookRepository;
import com.bridgelabs.repository.ContactRepository;
import com.bridgelabs.services.AddressBookService;
import com.bridgelabs.services.ContactService;
import com.bridgelabs.services.IAddressBookService;
import com.bridgelabs.services.IContactService;
import java.util.Scanner;

public class AddressBookMain {
    static ContactRepository contactRepository = new ContactRepository();
    static AddressBookRepository addressBookRepository = new AddressBookRepository();

    public static void main(String[] args) {
        IAddressBookService addressBookService = new AddressBookService(addressBookRepository);
        IAddressBookController addressBookController = new AddressBookController(addressBookService);
        IContactService service = new ContactService(contactRepository);
        IContactController controller = new ContactController(service);
        Scanner scanner = new Scanner(System.in);
        int op;
        int selectedOption;
        System.out.println("*********************** Welcome To Address Book System ***********************");
        do{
            System.out.println("Where you want to go");
            System.out.println("1 : Address Book");
            System.out.println("2 : Contacts");
            System.out.println("3 : Exit ");
            op = scanner.nextInt();
            switch (op){
                case 1:
                    do{
                        addressBookController.displayOptions();
                        selectedOption = addressBookController.chooseOptions();
                        addressBookController.invokeOption(selectedOption);
                    }while (selectedOption != 0);
                    break;
                case 2:
                    do {
                        controller.displayOptions();
                        selectedOption = controller.chooseOptions();
                        controller.invokeOption(selectedOption);
                    } while (selectedOption != 0);
                    break;
                case 3:
                    System.out.println("See You Again!!");
                    break;
                default:
                    System.out.println("Wrong Input!");
                    break;
            }
        }while (op!= 3);
    }
}
