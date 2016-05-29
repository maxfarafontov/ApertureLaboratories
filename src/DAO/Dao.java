package DAO; //Data Access Object


import Data.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Dao {
    public static final String URL = "jdbc:mysql://localhost:3306/dbfirst";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "19941124";
    public List<Person> persons = new ArrayList();
    private Connection connection;
    private Statement st;

    public Dao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPersons() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM users";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persons.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("surname"),
                        rs.getInt("age"), rs.getInt("passportNumber"), rs.getInt("passportSeries")));
            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return persons;
    }

    public void deleteUser(int id) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q = "DELETE from users where id =" + id;
        try {
            st.executeUpdate(q);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public Person getUserById(int id) {
        String q = "select id,name,surname,email,age,passportSeries,passportNumber from users where id=" + id;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = st.executeQuery(q);
            if (rs.next()) {
                Person person = new Person(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("surname"),
                        rs.getInt("age"), rs.getInt("passportNumber"), rs.getInt("passportSeries"));
                return person;
            }
            rs.close();
        } catch (SQLException e) {
        }
        return null;
    }

    public void addUser(Person u) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q = "insert into users (name,surname,email,age,passportSeries,passportNumber) values " + u.toString() + ";";
        try {
            st.executeUpdate(q);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void updateUser(Person u) {
        String q = "Update users set name ='" + u.getName() + "',surname='" + u.getSurname() + "',email='" + u.getEmail() +
                "',age=" + u.getAge() + ",passportSeries='" + u.getpassSeries() + "',passportNumber='" + u.getpassNumb() + "' where id=" + u.getId();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
            st.executeUpdate(q);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}