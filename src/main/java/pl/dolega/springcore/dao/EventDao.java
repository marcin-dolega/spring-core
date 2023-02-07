package pl.dolega.springcore.dao;

import org.springframework.stereotype.Component;
import pl.dolega.springcore.model.Event;

import java.util.Date;
import java.util.List;

@Component
public interface EventDao {

    Event getEventById(long eventId);
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);
    Event createEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);

}
