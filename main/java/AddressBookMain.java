import com.bridgelabs.controller.AddressBookController;
import com.bridgelabs.controller.IAddressBookController;
import com.bridgelabs.repository.AddressBookRepository;
import com.bridgelabs.services.AddressBookService;
import com.bridgelabs.services.IAddressBookService;

public class AddressBookMain {
    static AddressBookRepository addressBookRepository = new AddressBookRepository();
    public static void main(String[] args) {

        IAddressBookService addressBookService = new AddressBookService(addressBookRepository);
        IAddressBookController addressBookController= new AddressBookController(addressBookService);

        int selectedOption;
        do {
            addressBookController.displayOptions();
            selectedOption = addressBookController.chooseOptions();
            addressBookController.invokeOption(selectedOption);
        }while(selectedOption !=0);
    }
}
