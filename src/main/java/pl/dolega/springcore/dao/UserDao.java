package pl.dolega.springcore.dao;

import pl.dolega.springcore.model.user.User;

import java.util.List;

public interface UserDao{

    User getUserById(long userId);
    User getUserByEmail(String email);
    List<User> getUsersByName(String name, int pageSize, int pageNum);
    User createUser(User user);
    User updateUser(User user);
    boolean deleteUser(long userId);


}
