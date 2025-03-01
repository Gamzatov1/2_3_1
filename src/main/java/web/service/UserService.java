package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    void saveUser(User user);

    User getOne(long id);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}