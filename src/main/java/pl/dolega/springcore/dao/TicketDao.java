package pl.dolega.springcore.dao;


import pl.dolega.springcore.model.event.Event;
import pl.dolega.springcore.model.ticket.Ticket;
import pl.dolega.springcore.model.user.User;

import java.util.List;

public interface TicketDao {

    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);
    Ticket getBookedTicketById(long ticketId);
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
    boolean cancelTicket(long ticketId);

}
