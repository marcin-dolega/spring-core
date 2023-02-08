package pl.dolega.springcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dolega.springcore.dao.TicketDao;
import pl.dolega.springcore.dao.impl.TicketDaoImpl;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.Ticket;
import pl.dolega.springcore.model.User;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketDaoTests {

    @Autowired
    TicketDao ticketDao;

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    User user;
    Event event;
    List<Ticket> ticketList;

    @BeforeEach
    public void setupUserEvent() {
        user = User.builder()
                .id(1)
                .name("user")
                .email("user@email.com")
                .build();
        event = Event.builder()
                .id(1)
                .title("event_1")
                .date(new Date())
                .build();
    }

    @Test
    public void bookTicketTest() {
        Ticket ticket = ticketDao.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.STANDARD);
        assertNotNull(ticketStorage.get("ticket:" + ticket.getId()));
    }

    @Test
    public void getBookedTicketsByUserTest() {
        for (int i = 0; i < 4; i++) {
            ticketStorage.put("ticket:" + i, Ticket.builder().userId(1).build());
        }
        ticketList = ticketDao.getBookedTickets(user, 2, 1);
        assertEquals(2, ticketList.size());
    }

    @Test
    public void getBookedTicketsByEventTest() {
        for (int i = 0; i < 4; i++) {
            ticketStorage.put("ticket:" + i, Ticket.builder().eventId(1).build());
        }
        ticketList = ticketDao.getBookedTickets(event, 2, 1);
        assertEquals(2, ticketList.size());
    }

    @Test
    public void cancelTicketTrueTest() {
        Ticket ticket = ticketDao.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.STANDARD);
        boolean result = ticketDao.cancelTicket(ticket.getId());
        assertTrue(result);
    }

    @Test
    public void cancelTicketFalseTest() {
        Ticket ticket = Ticket.builder().build();
        boolean result = ticketDao.cancelTicket(ticket.getId());
        assertFalse(result);
    }
}
