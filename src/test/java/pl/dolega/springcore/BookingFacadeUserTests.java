package pl.dolega.springcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dolega.springcore.facade.BookingFacade;
import pl.dolega.springcore.model.user.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingFacadeUserTests {

    @Autowired
    BookingFacade bookingFacade;

    @Autowired
    LinkedHashMap<String, User> userStorage;
    User user;

    @BeforeEach
    public void setupUser() {
        userStorage.clear();
        user = User.builder()
                .id(1)
                .name("user")
                .email("user@email.com")
                .build();
    }

    @Test
    public void getUserByIdTest() {
        bookingFacade.createUser(user);
        User fetch = bookingFacade.getUserById(user.getId());
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

        usersByName = bookingFacade.getUsersByName("user", 4, 1);
        assertEquals(4, usersByName.size());
    }

    @Test
    public void getUserByEmailTest() {
        bookingFacade.createUser(user);
        User fetch = bookingFacade.getUserByEmail("user@email.com");
        assertNotNull(fetch);
    }

    @Test
    public void createUserTest() {
        bookingFacade.createUser(user);
        assertNotNull(user);
        assertNotNull(userStorage);
        assertEquals("user", userStorage.get("user:" + user.getId()).getName());
    }

    @Test
    public void createUsersWithSameIdTest() {
        User user1 = User.builder()
                .id(1)
                .name("user")
                .email("user1@email.com")
                .build();
        User user2 = User.builder()
                .id(1)
                .name("user")
                .email("user2@email.com")
                .build();
        bookingFacade.createUser(user1);
        bookingFacade.createUser(user2);
        assertEquals(1, userStorage.size());
    }

    @Test
    public void createUsersWithSameEmailTest() {
        User user1 = User.builder()
                .id(1)
                .name("user")
                .email("user@email.com")
                .build();
        User user2 = User.builder()
                .id(2)
                .name("user")
                .email("user@email.com")
                .build();
        bookingFacade.createUser(user1);
        bookingFacade.createUser(user2);
        assertEquals(1, userStorage.size());
    }


    @Test
    public void updateUserTest() {

        User update = User.builder()
                .id(user.getId())
                .name("update")
                .email("update@email.com")
                .build();

        bookingFacade.createUser(user);
        bookingFacade.updateUser(update);

        assertEquals("update", userStorage.get("user:" + user.getId()).getName());
    }

    @Test
    public void deleteUserTrueTest() {
        bookingFacade.createUser(user);
        boolean result = bookingFacade.deleteUser(user.getId());
        assertTrue(result);
    }

    @Test
    public void deleteUserFalseTest() {
        boolean result = bookingFacade.deleteUser(user.getId());
        assertFalse(result);
    }
}
