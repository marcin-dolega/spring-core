package pl.dolega.springcore.dao;


import org.springframework.stereotype.Component;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.Ticket;
import pl.dolega.springcore.model.User;

import java.util.List;

public interface TicketDao {

    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
    boolean cancelTicket(long ticketId);

}
