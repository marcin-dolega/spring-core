package pl.dolega.springcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dolega.springcore.dao.EventDao;
import pl.dolega.springcore.model.Event;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventDaoTests {

    @Autowired
    EventDao eventDao;

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    Event event;

    @BeforeEach
    public void setupEvent() {
        eventStorage.clear();
        event = Event.builder()
                .id(1)
                .title("event_1")
                .date(new Date())
                .build();
    }

    @Test
    public void getEventByIdTest() {
        eventDao.createEvent(event);
        Event fetched = eventDao.getEventById(event.getId());
        assertNotNull(fetched);
    }

    @Test
    public void getEventsByTitleTest() {
        List<Event> eventList = new ArrayList<>();
        List<Event> eventsByTitle;
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) {
                eventList.add(Event.builder()
                        .id(i)
                        .title("title_1")
                        .date(new Date())
                        .build());
            } else {
                eventList.add(Event.builder()
                        .id(i)
                        .title("title_2")
                        .date(new Date())
                        .build());
            }
        }
        for (Event e : eventList) {
            eventStorage.put("event:" + e.getId(), e);
        }
        eventsByTitle = eventDao.getEventsByTitle("title_1", 4, 1);
        assertEquals(4, eventsByTitle.size());
    }

    @Test
    public void getEventsForDayTest() {
        List<Event> eventList = new ArrayList<>();
        List<Event> eventsByDay;
        for (int i = 0; i < 16; i++) {
            Date date = new Date();
            if (i % 2 == 0) {
                date.setDate(1);
                eventList.add(Event.builder()
                        .id(i)
                        .title("title_1")
                        .date(date)
                        .build());
            } else {
                date.setDate(2);
                eventList.add(Event.builder()
                        .id(i)
                        .title("title_2")
                        .date(date)
                        .build());
            }
        }
        eventsByDay = eventDao.getEventsForDay(eventList.get(0).getDate(), 4, 2);
        assertNotNull(eventsByDay);
    }

    @Test
    public void createEventTest() {
        eventDao.createEvent(event);
        assertNotNull(event);
        assertNotNull(eventStorage);
        assertEquals("event_1", eventStorage.get("event:" + event.getId()).getTitle());
    }

    @Test
    public void updateEventTest() {
        Event update = Event.builder()
                .id(event.getId())
                .title("event_2")
                .date(Date.from(Instant.now()))
                .build();

        eventDao.createEvent(event);
        eventDao.updateEvent(update);

        assertEquals(eventStorage.get("event:" + event.getId()).getTitle(), "event_2");
    }

    @Test
    public void deleteEventTrueTest() {
        eventDao.createEvent(event);
        boolean result = eventDao.deleteEvent(event.getId());
        assertTrue(result);
    }

    @Test
    public void deleteEventFalseTest() {
        boolean result = eventDao.deleteEvent(event.getId());
        assertFalse(result);
    }
}
