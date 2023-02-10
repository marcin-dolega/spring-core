package pl.dolega.springcore.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.UserDao;
import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.exceptions.RecordAlreadyExistException;
import pl.dolega.springcore.model.User;
import pl.dolega.springcore.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    Utils utils = new Utils();

    @Override
    public User getUserById(long userId) {
        return userStorage.get("user:" + userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userStorage.values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        int skipCount = (pageNum - 1) * pageSize;
        return userStorage.values()
                .stream()
                .filter(user -> user.getName().equals("user"))
                .skip(skipCount)
                .limit(pageSize)
                .toList();
    }

    @Override
    public User createUser(User user) {
        if (utils.doesExist("user", user.getId(), userStorage)) {
            return user;
        }
        return userStorage.put("user:" + user.getId(), user);
    }

    @Override
    public User updateUser(User user) {
        if (utils.doesntExist("user", user.getId(), userStorage)) {
            userStorage.put("user:" + user.getId(), user);
        }
        return userStorage.replace("user:" + user.getId(), user);
    }

    @Override
    public boolean deleteUser(long userId) {
        if (utils.doesntExist("user", userId, userStorage)) {
            return false;
        }
        userStorage.remove("user:" + userId);
        return true;
    }

}
