import com.bridgelabs.controller.ContactController;
import com.bridgelabs.controller.IContactController;
import com.bridgelabs.repository.ContactRepository;
import com.bridgelabs.services.ContactService;
import com.bridgelabs.services.IContactService;

public class AddressBookMain {
    static ContactRepository contactRepository = new ContactRepository();

    public static void main(String[] args) {
        IContactService service = new ContactService(contactRepository);
        IContactController controller = new ContactController(service);
        int selectedOption;
        do{
            controller.displayOptions();
            selectedOption = controller.chooseOptions();
            controller.invokeOption(selectedOption);
        }while (selectedOption !=0);
    }
}
