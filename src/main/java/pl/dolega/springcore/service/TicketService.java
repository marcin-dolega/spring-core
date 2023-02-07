package pl.dolega.springcore.service;

import org.springframework.stereotype.Service;
import pl.dolega.springcore.dao.TicketDao;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.Ticket;
import pl.dolega.springcore.model.User;

import java.util.List;

@Service
public class TicketService implements TicketDao {

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return false;
    }
}
