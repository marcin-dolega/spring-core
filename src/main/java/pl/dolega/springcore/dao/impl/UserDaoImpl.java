package pl.dolega.springcore.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.UserDao;
import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.exceptions.RecordAlreadyExistException;
import pl.dolega.springcore.model.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    @Override
    public User getUserById(long userId) {
        return userStorage.get("user:" + userId);
    }

    @Override
    public User getUserByEmail(String email) {
        for (String key : userStorage.keySet()) {
            if (userStorage.get(key).getEmail().equals(email)) {
                return userStorage.get(key);
            }
        }
        return null;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        List<User> userList = new ArrayList<>();
        List<User> usersByName = new ArrayList<>();
        userStorage.forEach((s, user) -> userList.add(user));
        int start = pageNum * pageSize;
        int end = pageSize * (pageNum + 1);
        for (int i = start; i < end; i++) {
            if (userList.get(i).getName().equals(name)) {
                usersByName.add(userList.get(i));
            }
        }
        return usersByName;
    }

    @Override
    public User createUser(User user) {
        if (doesExist(user.getId())) {
            return user;
        }
        return userStorage.put("user:" + user.getId(), user);
    }

    @Override
    public User updateUser(User user) {
        return userStorage.replace("user:" + user.getId(), user);
    }

    @Override
    public boolean deleteUser(long userId) {
        if (doesntExist(userId)) {
            return false;
        }
        userStorage.remove("user:" + userId);
        return true;
    }

    private boolean doesExist(long userId) {
        if (getUserById(userId) == null) {
            try {
                throw new RecordAlreadyExistException("user:" + userId + " already exist");
            } catch (RecordAlreadyExistException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private boolean doesntExist(long userId) {
        if (getUserById(userId) != null) {
            try {
                throw new NoSuchRecordException("user:" + userId + " doesn't exist");
            } catch (NoSuchRecordException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
