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

    public int add(Person person) {
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
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
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