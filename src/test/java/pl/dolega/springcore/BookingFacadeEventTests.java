package pl.dolega.springcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dolega.springcore.facade.BookingFacade;
import pl.dolega.springcore.model.event.Event;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingFacadeEventTests {

    @Autowired
    BookingFacade bookingFacade;

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
        bookingFacade.createEvent(event);
        Event fetched = bookingFacade.getEventById(event.getId());
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
        eventsByTitle = bookingFacade.getEventsByTitle("title_1", 4, 1);
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
        eventsByDay = bookingFacade.getEventsForDay(eventList.get(0).getDate(), 4, 2);
        assertNotNull(eventsByDay);
    }

    @Test
    public void createEventTest() {
        bookingFacade.createEvent(event);
        System.out.println(eventStorage);
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

        bookingFacade.createEvent(event);
        bookingFacade.updateEvent(update);

        assertEquals(eventStorage.get("event:" + event.getId()).getTitle(), "event_2");
    }

    @Test
    public void deleteEventTrueTest() {
        bookingFacade.createEvent(event);
        boolean result = bookingFacade.deleteEvent(event.getId());
        assertTrue(result);
    }

    @Test
    public void deleteEventFalseTest() {
        boolean result = bookingFacade.deleteEvent(event.getId());
        assertFalse(result);
    }
}
