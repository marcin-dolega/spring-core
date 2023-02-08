package pl.dolega.springcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.UserDao;
import pl.dolega.springcore.model.User;

import java.util.List;

public class UserService implements UserDao {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDao.deleteUser(userId);
    }
}
