package pl.dolega.springcore.service;

import org.springframework.stereotype.Service;
import pl.dolega.springcore.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public User getUserById(long userId) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(long userId) {
        return false;
    }
}
