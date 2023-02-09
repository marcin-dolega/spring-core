package pl.dolega.springcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dolega.springcore.dao.UserDao;
import pl.dolega.springcore.model.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDaoTests {

    @Autowired
    UserDao userDao;

    @Autowired
    LinkedHashMap<String, User> userStorage;

    User user;

    @BeforeEach
    public void setupUser() {
        user = User.builder()
                .id(1)
                .name("user")
                .email("user@email.com")
                .build();
    }

    @Test
    public void getUserByIdTest() {
        userDao.createUser(user);
        User fetch = userDao.getUserById(user.getId());
        assertNotNull(fetch);
    }

    @Test
    public void getUsersByNameTest() {
        List<User> userList = new ArrayList<>();
        List<User> usersByName;
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) {
                userList.add(User.builder()
                        .id(i)
                        .name("user")
                        .build());
            } else {
                userList.add(User.builder()
                        .id(i)
                        .name("temp")
                        .build());
            }
        }

        for (User u : userList) {
            userStorage.put("user:" + u.getId(), u);
        }

        usersByName = userDao.getUsersByName("user", 4, 1);
        assertEquals(4, usersByName.size());
    }

    @Test
    public void getUserByEmailTest() {
        userDao.createUser(user);
        User fetch = userDao.getUserByEmail("user@email.com");
        assertNotNull(fetch);
    }

    @Test
    public void createUserTest() {
        userStorage.clear();
        userDao.createUser(user);
        System.out.println(user);
        assertNotNull(user);
        assertNotNull(userStorage);
        assertEquals("user", userStorage.get("user:" + user.getId()).getName());
    }

    @Test
    public void updateUserTest() {

        User update = User.builder()
                .id(user.getId())
                .name("update")
                .email("update@email.com")
                .build();

        userDao.createUser(user);
        System.out.println(userDao.getUserById(user.getId()));
        userDao.updateUser(update);
        System.out.println(userDao.getUserById(user.getId()));

        assertEquals("update", userStorage.get("user:" + user.getId()).getName());
    }

    @Test
    public void deleteUserTrueTest() {
        userDao.createUser(user);
        boolean result = userDao.deleteUser(user.getId());
        assertTrue(result);
    }

    @Test
    public void deleteUserFalseTest() {
        userStorage.clear();
        boolean result = userDao.deleteUser(user.getId());
        assertFalse(result);
    }

}
