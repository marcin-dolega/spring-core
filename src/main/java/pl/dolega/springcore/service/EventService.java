package pl.dolega.springcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.EventDao;
import pl.dolega.springcore.dao.impl.EventDaoImpl;
import pl.dolega.springcore.model.event.Event;

import java.util.Date;
import java.util.List;

public class EventService {

    @Autowired
    EventDao eventDao;

    public Event getEventById(long eventId) {
        return eventDao.getEventById(eventId);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDao.getEventsByTitle(title, pageSize, pageNum);
    }

    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventDao.getEventsForDay(day, pageSize, pageNum);
    }

    public Event createEvent(Event event) {
        return eventDao.createEvent(event);
    }

    public Event updateEvent(Event event) {
        return eventDao.updateEvent(event);
    }

    public boolean deleteEvent(long eventId) {
        return eventDao.deleteEvent(eventId);
    }
}
