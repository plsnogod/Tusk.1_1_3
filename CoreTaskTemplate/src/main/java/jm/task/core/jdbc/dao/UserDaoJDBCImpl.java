package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = Util.getConnectionJDBC();

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        String sqlcommand = "CREATE TABLE IF NOT EXISTS users" +
                "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(45), " +
                "lastname VARCHAR(45), " +
                "age INT NOT NULL" +
                ") ENGINE=InnoDB";
        try (Connection connection = Util.getConnectionJDBC()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlcommand);
            System.out.println("Creation of table successfully");
        } catch (SQLException throwables) {
            System.err.println("Failed to create table");
            throwables.printStackTrace();
        }


    }

    public void dropUsersTable() {

        try (Connection connection = Util.getConnectionJDBC()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE users");
            System.out.println("\n" +
                    "Dropping the table was successful");
        } catch (SQLException throwables) {
            System.out.println("Failed to drop table");
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnectionJDBC()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name,lastname,age) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("user named " + name + " added");
        } catch (SQLException s) {
            System.out.println("Don't insert");
            s.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnectionJDBC()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try (Connection connection = Util.getConnectionJDBC()) {
             statement = connection.createStatement();
             resultSet = statement.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);

            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
            System.out.println("Table truncate");
        } catch (SQLException s) {
            System.err.println("Table don't truncate");
            s.printStackTrace();
        }

    }
}
