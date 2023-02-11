package pl.dolega.springcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.UserDao;
import pl.dolega.springcore.model.user.User;

import java.util.List;

public class UserService {

    @Autowired
    UserDao userDao;

    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getUsersByName(name, pageSize, pageNum);
    }

    public User createUser(User user) {
        return userDao.createUser(user);
    }

    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    public boolean deleteUser(long userId) {
        return userDao.deleteUser(userId);
    }
}
