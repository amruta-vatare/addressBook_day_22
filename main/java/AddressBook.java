import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<Person> contactList;
    private String name;
    public AddressBook(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getContactList() {
        return contactList;
    }

    public void setContactList(List<Person> contactList) {
        this.contactList = contactList;
    }


}
