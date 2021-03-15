package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
//      userService.createUsersTable();
      userService.saveUser("Bob", "Silent", (byte) 32);
      userService.saveUser("Ivan", "Vilkov", (byte) 25);
      userService.saveUser("Petr", "Petrov", (byte) 53);
      userService.saveUser("Sidor", "Sidorov", (byte) 77);
    // userService.removeUserById(4);
       //userService.getAllUsers();
       // System.out.println(userService.getAllUsers());
       // userService.dropUsersTable();
      //  userService.cleanUsersTable();


    }
}
