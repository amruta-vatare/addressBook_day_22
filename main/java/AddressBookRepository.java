import java.util.LinkedList;

public class AddressBookRepository extends LinkedList<Person> {
    private String name;
    public AddressBookRepository(){}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
