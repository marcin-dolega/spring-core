package pl.dolega.springcore.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.EventDao;
import pl.dolega.springcore.model.event.Event;
import pl.dolega.springcore.utils.RecordChecker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventDaoImpl implements EventDao {

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    RecordChecker utils = new RecordChecker();

    private static final Logger logger = LoggerFactory.getLogger(EventDao.class);

    @Override
    public Event getEventById(long eventId) {
        logger.info("Getting event by id: " + eventId);
        return eventStorage.get("event:" + eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        logger.info("Getting events of title: " + title + ".");
        int skipCount = (pageNum - 1) * pageSize;
        return eventStorage.values()
                .stream()
                .filter(event -> event.getTitle().equals(title))
                .skip(skipCount)
                .limit(pageSize)
                .toList();
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        try {
            logger.info("Getting events for: " + String.valueOf(getDateWithoutTime(day)).substring(4, 10) + ".");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int skipCount = (pageNum - 1) * pageSize;
        return eventStorage.values()
                .stream()
                .filter(event -> {
                    try {
                        return getDateWithoutTime(event.getDate()).equals(day);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .skip(skipCount)
                .limit(pageSize)
                .toList();
    }

    @Override
    public Event createEvent(Event event) {
        if (utils.doesExist("event", event.getId(), eventStorage)) {
            logger.error("Event of id: " + event.getId() + " already exists.");
            return event;
        }
        eventStorage.put("event:" + event.getId(), event);
        logger.info(event + " created.");
        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        if (utils.doesntExist("event", event.getId(), eventStorage)) {
            return event;
        }
        eventStorage.replace("event:" + event.getId(), event);
        logger.info("Event of id: " + event.getId() + " updated.");
        return event;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        if (utils.doesntExist("event", eventId, eventStorage)) {
            return false;
        }
        eventStorage.remove("event:" + eventId);
        logger.info("Event of id: " + eventId + " deleted.");
        return true;
    }

    private Date getDateWithoutTime(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(formatter.format(date));
    }

}
