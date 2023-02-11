package pl.dolega.springcore.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.UserDao;
import pl.dolega.springcore.model.User;
import pl.dolega.springcore.utils.RecordChecker;

import java.util.LinkedHashMap;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    RecordChecker utils = new RecordChecker();

    Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User getUserById(long userId) {
        logger.info("Getting user by id: " + userId + ".");
        return userStorage.get("user:" + userId);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("Getting user by email: " + email + ".");
        return userStorage.values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        logger.info("Getting users by name: " + name + ".");
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
        logger.info("User created.");
        return userStorage.put("user:" + user.getId(), user);
    }

    @Override
    public User updateUser(User user) {
        if (utils.doesntExist("user", user.getId(), userStorage)) {
            userStorage.put("user:" + user.getId(), user);
        }
        logger.info("Updating user by id: " + user.getId() + ".");
        return userStorage.replace("user:" + user.getId(), user);
    }

    @Override
    public boolean deleteUser(long userId) {
        if (utils.doesntExist("user", userId, userStorage)) {
            return false;
        }
        logger.info("Deleting user of id: " + userId + ".");
        userStorage.remove("user:" + userId);
        return true;
    }

}
