package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        User user1 = new User("Name1", "LastName1", (byte) 20);
        User user2 = new User("Name2", "LastName2", (byte) 25);
        User user3 = new User("Name3", "LastName3", (byte) 31);
        User user4 = new User("Name4", "LastName4", (byte) 38);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        for (User elem : userList) {
            userDao.saveUser(elem.getName(), elem.getLastName(), elem.getAge());
            System.out.println("User с именем - " + elem.getName() + " добавлен в базу данных");
        }

        userDao.removeUserById(1);

        List<User> select = userDao.getAllUsers();
        select.stream().forEach(System.out::println);

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        Util.sessionFactory.close();
    }
}
