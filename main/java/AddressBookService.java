import java.util.List;

public class AddressBookService implements IAddressBookService{
    AddressBookRepository addressBookRepository;
    AddressBookService(AddressBookRepository addressBookRepository){
        this.addressBookRepository = addressBookRepository;
    }

    @Override
    public void add(Person person) {
        addressBookRepository.add(person);
    }

    @Override
    public Person get(String firstName) {
        for (Person person: addressBookRepository) {
            if(person.getFirstName().equals(firstName)){
                return person;
            }
        }
        return null;
    }

    @Override
    public boolean delete(String firstName) {
        for (Person person:addressBookRepository) {
            if(person.getFirstName().equals(firstName)){
                addressBookRepository.remove(person);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean edit(String firstName, Person person) {
        Person existingPerson = get(firstName);
        if (existingPerson == null) {
            return false;
        }
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setAddress(person.getAddress());
        existingPerson.setCity(person.getCity());
        existingPerson.setZipCode(person.getZipCode());
        existingPerson.setState(person.getState());
        existingPerson.setPhoneNumber(person.getPhoneNumber());
        existingPerson.setEmailId(person.getEmailId());
        return true;
    }

    @Override
    public List<Person> getAll() {
        return addressBookRepository;
    }
}
