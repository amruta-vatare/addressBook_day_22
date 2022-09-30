package com.bridgelabs.repository;

import com.bridgelabs.database.Mydatabase;
import com.bridgelabs.database.MysqlDatabase;
import com.bridgelabs.models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    Mydatabase mydatabase = new MysqlDatabase();
    Connection con = null;
    public ContactRepository() {
        con = mydatabase.createConnection();
    }

    public int add(String addressBookName,Person person) {
        int res = 0;
        try {
            String insertQuery = "insert into contact(first_name ,last_name ,address ,city ,state ,zip_code ,phone_no ,email_id) values(?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(insertQuery);
            st.setString(1, person.getFirstName());
            st.setString(2, person.getLastName());
            st.setString(3, person.getAddress());
            st.setString(4, person.getCity());
            st.setString(5, person.getState());
            st.setInt(6, person.getZipCode());
            st.setLong(7, person.getPhoneNumber());
            st.setString(8, person.getEmailId());
            res = st.executeUpdate();
            System.out.println(res + " are affected");
            addToAddressBook(addressBookName,person.getEmailId());
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void addToAddressBook(String addressBookName,String email) {
        String insertQuery = "insert into address_book_contacts_mapping(adds_book_id,contact_id) values((select adds_book_id from address_book where address_book_name = ?)  ,(select contact_id from contact where email_id = ?))";
        PreparedStatement st = null;
        int res = 0;
        try {
            st = con.prepareStatement(insertQuery);
            st.setString(1, addressBookName);
            st.setString(2,email);
            res = st.executeUpdate();
            if(res!=0){
                System.out.println("Successfully added to address Book");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAll() {
        List<Person> contacts = new ArrayList<>();
        try {
            String getAllQuery = "select * from contact";
            PreparedStatement st = con.prepareStatement(getAllQuery);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Person contact = new Person();
                System.out.println(resultSet.getInt("contact_id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setAddress(resultSet.getString("address"));
                contact.setCity(resultSet.getString("city"));
                contact.setState(resultSet.getString("state"));
                contact.setZipCode(resultSet.getInt("zip_code"));
                contact.setPhoneNumber(resultSet.getLong("phone_no"));
                contact.setEmailId(resultSet.getString("email_id"));
                contacts.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public int update(String email_id, Person person) {
        List<Person> contacts = getAll();
        int res = 0;
        try {
            int id = get(email_id);
            String updateQuery = "update contact set first_name = ?, last_name = ?, address = ?, city = ?, state = ?, zip_code = ?, phone_no = ?, email_id = ? where contact_id = ?";
            PreparedStatement st = con.prepareStatement(updateQuery);
            st.setString(1, person.getFirstName());
            st.setString(2, person.getLastName());
            st.setString(3, person.getAddress());
            st.setString(4, person.getCity());
            st.setString(5, person.getState());
            st.setInt(6, person.getZipCode());
            st.setLong(7, person.getPhoneNumber());
            st.setString(8, person.getEmailId());
            st.setInt(9, id);
            res = st.executeUpdate();
            System.out.println(res + " are affected");
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int get(String email_id) {
        int id = 0;
        try {
            String getAllQuery = "select contact_id from contact where email_id = ?";
            PreparedStatement st = con.prepareStatement(getAllQuery);
            st.setString(1, email_id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("contact_id");
                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int delete(String email_id) {
        int id = get(email_id);
        int affectedRow = 0;
        try {
            String getAllQuery = "delete from contact where contact_id = ?";
            PreparedStatement st = con.prepareStatement(getAllQuery);
            st.setInt(1, id);
            affectedRow = st.executeUpdate();
            return affectedRow;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRow;
    }

    public void getAllWithAddressBookAndType() {
        ResultSet rs = null;
        String query = "SELECT * FROM contact b" +
                "  INNER JOIN address_book_contacts_mapping a ON a.contact_id = b.contact_id " +
                "  INNER JOIN address_book c ON c.adds_book_id = a.adds_book_id" +
                "  INNER JOIN address_book_type t ON t.book_type_id = c.address_book_type";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            System.out.println("first_name\tlast_name\tcity\tstate\tphone_no\temail_id\taddress_book_name\ttype_name");
            System.out.println("-----------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.println();
                System.out.print(rs.getString("first_name")+"\t\t");
                System.out.print(rs.getString("last_name")+"\t\t");
                System.out.print(rs.getString("city")+"\t\t");
                System.out.print(rs.getString("state")+"\t\t");
                System.out.print(rs.getLong("phone_no")+"\t\t");
                System.out.print(rs.getString("email_id")+"\t\t");
                System.out.print(rs.getString("address_book_name")+"\t\t");
                System.out.print(rs.getString("type_name")+"\t\t");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getContactByCity(String city) {
        ArrayList<Person> contacts = new ArrayList<>();
        String query = "select * from contact where city = ?";
        try {
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,city);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Person contact = new Person();
                System.out.println(rs.getInt("contact_id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setAddress(rs.getString("address"));
                contact.setCity(rs.getString("city"));
                contact.setState(rs.getString("state"));
                contact.setZipCode(rs.getInt("zip_code"));
                contact.setPhoneNumber(rs.getLong("phone_no"));
                contact.setEmailId(rs.getString("email_id"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<Person> getContactByState(String state) {
        ArrayList<Person> contacts = new ArrayList<>();
        String query = "select * from contact where state = ?";
        try {
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,state);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Person contact = new Person();
                System.out.println(rs.getInt("contact_id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setAddress(rs.getString("address"));
                contact.setCity(rs.getString("city"));
                contact.setState(rs.getString("state"));
                contact.setZipCode(rs.getInt("zip_code"));
                contact.setPhoneNumber(rs.getLong("phone_no"));
                contact.setEmailId(rs.getString("email_id"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    /*public List<Person> getContactByCity(String city) {
        List<Person> contacts = new ArrayList<>();
        try {
            String getAllQuery = "select * from contact";
            PreparedStatement st = con.prepareStatement(getAllQuery);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Person contact = new Person();
                System.out.println(resultSet.getInt("contact_id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setAddress(resultSet.getString("address"));
                contact.setCity(resultSet.getString("city"));
                contact.setState(resultSet.getString("state"));
                contact.setZipCode(resultSet.getInt("zip_code"));
                contact.setPhoneNumber(resultSet.getLong("phone_no"));
                contact.setEmailId(resultSet.getString("email_id"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }*/
}