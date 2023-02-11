package pl.dolega.springcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dolega.springcore.facade.BookingFacade;
import pl.dolega.springcore.model.event.Event;
import pl.dolega.springcore.model.ticket.Ticket;
import pl.dolega.springcore.model.user.User;

import java.util.Date;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    BookingFacade bookingFacade;

    @Autowired
    LinkedHashMap<String, User> userStorage;

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    User user;
    Event event;
    Ticket ticket;

    @BeforeEach
    public void clearStorage() {
        userStorage.clear();
        eventStorage.clear();
        ticketStorage.clear();
    }

    @Test
    public void createUserEventBookTicketTest() {
        user = bookingFacade.createUser(User.builder().id(1).name("user_1").email("user_1@email.com").build());
        event = bookingFacade.createEvent(Event.builder().id(1).title("event_1").date(new Date()).build());
        ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.STANDARD);
        assertNotNull(user);
        assertNotNull(event);
        assertNotNull(ticket);
    }

    @Test
    public void createUserEventBookCancelTicketSuccessTest() {
        user = bookingFacade.createUser(User.builder().id(1).name("user_1").email("user_1@email.com").build());
        event = bookingFacade.createEvent(Event.builder().id(1).title("event_1").date(new Date()).build());
        ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.STANDARD);
        System.out.println(ticket);
        boolean result = bookingFacade.cancelTicket(1);
        assertNotNull(user);
        assertNotNull(event);
        assertNotNull(ticket);
        assertTrue(result);
    }

    @Test
    public void createUserEventBookCancelTicketFailTest() {
        user = bookingFacade.createUser(User.builder().id(1).name("user_1").email("user_1@email.com").build());
        event = bookingFacade.createEvent(Event.builder().id(1).title("event_1").date(new Date()).build());
        ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.STANDARD);
        boolean result = bookingFacade.cancelTicket(2);
        assertNotNull(user);
        assertNotNull(event);
        assertNotNull(ticket);
        assertFalse(result);
    }
}
