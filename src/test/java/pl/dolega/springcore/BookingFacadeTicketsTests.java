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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class BookingFacadeTicketsTests {

    @Autowired
    BookingFacade bookingFacade;

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    @Autowired
    LinkedHashMap<String, User> userStorage;

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    User user;
    Event event;
    List<Ticket> ticketList;

    @BeforeEach
    public void setupUserEvent() {
        userStorage.clear();
        eventStorage.clear();
        user = User.builder()
                .id(1)
                .name("user_1")
                .email("user@email.com")
                .build();
        userStorage.put("user:" + user.getId(), user);
        event = Event.builder()
                .id(1)
                .title("event_1")
                .date(new Date())
                .build();
        eventStorage.put("event:" + event.getId(), event);
    }

    @Test
    public void bookTicketTest() {
        Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.STANDARD);
        assertNotNull(ticketStorage.get("ticket:" + ticket.getId()));
    }

    @Test
    public void getBookedTicketsByUserTest() {
        for (int i = 0; i < 4; i++) {
            ticketStorage.put("ticket:" + i, Ticket.builder().id(i).userId(1).build());
        }
        ticketList = bookingFacade.getBookedTickets(user, 2, 1);
        assertEquals(2, ticketList.size());
    }

    @Test
    public void getBookedTicketsByEventTest() {
        for (int i = 0; i < 4; i++) {
            ticketStorage.put("ticket:" + i, Ticket.builder().eventId(1).build());
        }
        ticketList = bookingFacade.getBookedTickets(event, 2, 1);
        assertEquals(2, ticketList.size());
    }

    @Test
    public void cancelTicketTrueTest() {
        Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.STANDARD);
        boolean result = bookingFacade.cancelTicket(ticket.getId());
        assertTrue(result);
    }

    @Test
    public void cancelTicketFalseTest() {
        Ticket ticket = Ticket.builder().build();
        boolean result = bookingFacade.cancelTicket(ticket.getId());
        assertFalse(result);
    }
}
