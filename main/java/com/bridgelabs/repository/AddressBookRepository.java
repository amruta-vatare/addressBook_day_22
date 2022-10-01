package com.bridgelabs.repository;

import com.bridgelabs.database.Mydatabase;
import com.bridgelabs.database.MysqlDatabase;
import com.bridgelabs.models.AddressBook;
import com.bridgelabs.models.AddressBookType;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookRepository {
    ArrayList<AddressBook> bookArrayList = new ArrayList<>();
    Connection con;
    Mydatabase mydatabase = new MysqlDatabase();

    public AddressBookRepository() {
        con = mydatabase.createConnection();
    }

    public int add(AddressBook addressBook) {
        int res = 0;
        try {
            String insertQuery = "insert into address_book(address_book_name,address_book_type) values(?,?)";
            PreparedStatement st = con.prepareStatement(insertQuery);
            st.setString(1, addressBook.getName());
            st.setInt(2, addressBook.getType());
            res = st.executeUpdate();
            System.out.println(res + " are affected");
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int delete(String name) {
        int id = get(name);
        int affectedRow = 0;
        try {
            String getAllQuery = "delete from address_book where adds_book_id = ?";
            PreparedStatement st = con.prepareStatement(getAllQuery);
            st.setInt(1, id);
            affectedRow = st.executeUpdate();
            return affectedRow;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRow;

    }

    public int get(String name) {
        int id = 0;
        try {
            String getAllQuery = "select adds_book_id from address_book where name = ?";
            PreparedStatement st = con.prepareStatement(getAllQuery);
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("adds_book_id");
                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<AddressBook> getAll() {
        try {
            String getAllQuery = "select * from address_book";
            PreparedStatement st = con.prepareStatement(getAllQuery);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                AddressBook book = new AddressBook();
                book.setAddress_book_id(resultSet.getInt("adds_book_id"));
                book.setName(resultSet.getString("address_book_name"));
                book.setType(resultSet.getInt("address_book_type"));
                bookArrayList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookArrayList;
    }

    public List<AddressBookType> getTypes() {
        List<AddressBookType> typeList = new ArrayList<>();
        try {
            String getAllQuery = "select * from address_book_type";
            PreparedStatement st = con.prepareStatement(getAllQuery);
            ResultSet resultSet = st.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("type_name"));
                    AddressBookType type = new AddressBookType();
                    type.setType_id(resultSet.getInt("book_type_id"));
                    type.setType_name(resultSet.getString("type_name"));
                    typeList.add(type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeList;
    }

    public Map<AddressBook, String> getAllWithType() {
        Map<AddressBook,String> bookTypeMap = new HashMap<>();
        try {
            String query = "SELECT * FROM address_book b INNER JOIN address_book_type t ON t.book_type_id = b.address_book_type";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet resultSet = st.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    AddressBook book = new AddressBook();
                    book.setName(resultSet.getString("address_book_name"));
                    String type = resultSet.getString("type_name");
                    bookTypeMap.put(book,type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookTypeMap;
    }
}