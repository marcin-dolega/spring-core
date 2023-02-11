package pl.dolega.springcore.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.TicketDao;
import pl.dolega.springcore.model.event.Event;
import pl.dolega.springcore.model.ticket.Ticket;
import pl.dolega.springcore.model.ticket.Ticket.Category;
import pl.dolega.springcore.model.user.User;
import pl.dolega.springcore.utils.RecordChecker;

import java.util.LinkedHashMap;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    @Autowired
    LinkedHashMap<String, Ticket> userStorage;

    @Autowired
    LinkedHashMap<String, Ticket> eventStorage;

    Logger logger = LoggerFactory.getLogger(TicketDao.class);

    RecordChecker utils = new RecordChecker();

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        if (utils.doesntExist("user", userId, userStorage) || utils.doesntExist("event", eventId, eventStorage)) {
            return null;
        }
        Ticket ticket = Ticket.builder()
                .id(ticketStorage.size() + 1)
                .userId(userId)
                .eventId(eventId)
                .place(place)
                .category(category)
                .build();
        if (utils.doesExist("ticket", ticket.getId(), ticketStorage)) {
            return ticketStorage.get("ticket:" + ticket.getId());
        }
        logger.info(ticket + " booked.");
        ticketStorage.put("ticket:" + ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        if (utils.doesntExist("user", user.getId(), userStorage)) {
            return null;
        }
        logger.info("Getting tickets by user id: " + user.getId() + ".");
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
        if (utils.doesntExist("event", event.getId(), eventStorage)) {
            return null;
        }
        logger.info("Getting tickets by event id: " + event.getId() + ".");
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
        if (utils.doesntExist("ticket", ticketId, ticketStorage)) {
            return false;
        }
        ticketStorage.remove("ticket:" + ticketId);
        logger.info(getBookedTicketById(ticketId) + " canceled.");
        return true;
    }

    @Override
    public Ticket getBookedTicketById(long ticketId) {
        return ticketStorage.get("ticket:" + ticketId);
    }

}
