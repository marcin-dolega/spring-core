package pl.dolega.springcore.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.TicketDao;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.Ticket;
import pl.dolega.springcore.model.Ticket.Category;
import pl.dolega.springcore.model.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class TicketDaoImpl implements TicketDao {

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        Ticket ticket = Ticket.builder()
                .id(ticketStorage.size() + 1)
                .userId(userId)
                .eventId(eventId)
                .place(place)
                .category(category)
                .build();
        return ticket;
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
