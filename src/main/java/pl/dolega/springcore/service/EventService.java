package pl.dolega.springcore.service;

import org.springframework.stereotype.Service;
import pl.dolega.springcore.dao.EventDao;
import pl.dolega.springcore.model.Event;

import java.util.Date;
import java.util.List;

@Service
public class EventService implements EventDao {

    @Override
    public Event getEventById(long eventId) {
        return null;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public Event createEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return false;
    }
}
