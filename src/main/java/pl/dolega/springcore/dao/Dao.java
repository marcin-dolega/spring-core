package pl.dolega.springcore.dao;

import pl.dolega.springcore.model.Event;

import java.util.Date;
import java.util.List;

public interface Dao<E>{

    E getEventById(long entityId);
    List<E> getEventsByTitle(String title, int pageSize, int pageNum);
    List<E> getEventsForDay(Date day, int pageSize, int pageNum);
    E createEvent(E entity);
    E updateEvent(E entity);
    boolean deleteEvent(long entityId);

}
