package pl.dolega.springcore.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.TicketDao;
import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.exceptions.RecordAlreadyExistException;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.Ticket;
import pl.dolega.springcore.model.Ticket.Category;
import pl.dolega.springcore.model.User;

import java.util.ArrayList;
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
        if (doesExist(ticket.getId())) {
            return ticketStorage.get("ticket:" + ticket.getId());
        }
        ticketStorage.put("ticket:" + ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
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
        if (doesntExist(ticketId)) {
            return false;
        }
        ticketStorage.remove("ticket:" + ticketId);
        return true;
    }

    private boolean doesExist(long ticketId) {
        if (ticketStorage.get("ticket:" + ticketId) != null) {
            try {
                throw new RecordAlreadyExistException("ticket:" + ticketId + " already exist");
            } catch (RecordAlreadyExistException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    private boolean doesntExist(long ticketId) {
        if (ticketStorage.get("ticket:" + ticketId) == null) {
            try {
                throw new NoSuchRecordException("ticket:" + ticketId + " doesn't exist");
            } catch (NoSuchRecordException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

}
