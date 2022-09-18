import java.util.List;

public interface IAddressBookService {
    void add(Person person);
    Person get(String name);
    boolean delete(String name);
    boolean edit(String name, Person person);
    List<Person> getAll();
}
