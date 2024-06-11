package web.crudwithboot.dao;

import web.crudwithboot.model.User;


import java.util.List;

public interface UserDao {
    void createUsersTable();

    void saveUser(User user);

    void removeUserById(long id);

    User getUserById(long id);
    List<User> getUsers();
    void updateUser(long id, User updatedUser);
}