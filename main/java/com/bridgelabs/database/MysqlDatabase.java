package com.bridgelabs.database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MysqlDatabase implements Mydatabase {
    static Connection con = null;
    @Override
    public Connection createConnection() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/mysql_application.properties"));
            String url = properties.getProperty("db_url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Connection Established successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
