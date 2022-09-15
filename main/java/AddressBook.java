import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<Person> contactList;
    public AddressBook(){
        contactList = new ArrayList<>();
    }
    public List<Person> getContactList() {
        return contactList;
    }

    public void setContactList(List<Person> contactList) {
        this.contactList = contactList;
    }


}
