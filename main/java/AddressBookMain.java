import com.bridgelabs.manager.AddressBookManager;
import com.bridgelabs.manager.IAddressBookManager;
import com.bridgelabs.repository.AddressBookRepository;
import com.bridgelabs.services.AddressBookService;
import com.bridgelabs.services.IAddressBookService;

public class AddressBookMain {
    static AddressBookRepository addressBookRepository = new AddressBookRepository();
    public static void main(String[] args) {
        IAddressBookService addressBookService = new AddressBookService(addressBookRepository);
        IAddressBookManager addressBookManager = new AddressBookManager(addressBookService);

        int selectedOption;
        do {
            addressBookManager.displayOptions();
            selectedOption = addressBookManager.chooseOptions();
            addressBookManager.invokeOption(selectedOption);
        }while(selectedOption !=0);
    }
}
