package pl.dolega.springcore.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.TicketDao;
import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.exceptions.RecordAlreadyExistException;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.Ticket;
import pl.dolega.springcore.model.Ticket.Category;
import pl.dolega.springcore.model.User;
import pl.dolega.springcore.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class TicketDaoImpl implements TicketDao {

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    @Autowired
    Utils utils;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        if (utils.doesntExist("user", userId) || utils.doesntExist("event", eventId)) {
            return null;
        }
        Ticket ticket = Ticket.builder()
                .id(ticketStorage.size() + 1)
                .userId(userId)
                .eventId(eventId)
                .place(place)
                .category(category)
                .build();
        if (utils.doesExist("ticket", ticket.getId())) {
            return ticketStorage.get("ticket:" + ticket.getId());
        }
        ticketStorage.put("ticket:" + ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        if (utils.doesntExist("user", user.getId())) {
            return null;
        }
        int skipCount = (pageNum - 1) * pageSize;
        return ticketStorage.values()
                .stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .skip(skipCount)
                .limit(pageSize)
                .toList();
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        if (utils.doesntExist("event", event.getId())) {
            return null;
        }
        int skipCount = (pageNum - 1) * pageSize;
        return ticketStorage.values()
                .stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .skip(skipCount)
                .limit(pageSize)
                .toList();
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        if (utils.doesntExist("ticket", ticketId)) {
            return false;
        }
        ticketStorage.remove("ticket:" + ticketId);
        return true;
    }

}
