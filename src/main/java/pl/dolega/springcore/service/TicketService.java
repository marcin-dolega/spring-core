package pl.dolega.springcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.TicketDao;
import pl.dolega.springcore.model.event.Event;
import pl.dolega.springcore.model.ticket.Ticket;
import pl.dolega.springcore.model.ticket.Ticket.Category;
import pl.dolega.springcore.model.user.User;

import java.util.List;

public class TicketService  {

    @Autowired
    TicketDao ticketDao;

    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        return ticketDao.bookTicket(userId, eventId, place, category);
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDao.getBookedTickets(user, pageSize, pageNum);
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDao.getBookedTickets(event, pageSize, pageNum);
    }

    public boolean cancelTicket(long ticketId) {
        return ticketDao.cancelTicket(ticketId);
    }
}
