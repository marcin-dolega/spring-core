package pl.dolega.springcore.bootstrap;

import jakarta.xml.bind.JAXBException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import pl.dolega.springcore.mapper.Mapper;
import pl.dolega.springcore.model.*;

import java.util.LinkedHashMap;

public class CustomProcessor implements BeanPostProcessor {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    boolean usersStored = false;
    boolean eventsStored = false;
    boolean ticketStored = false;


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("eventStorage"))
            System.out.println("Before:" + bean.getClass() + "  " + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (userStorage != null && !usersStored) {
            try {
                Users users = Mapper.mapUsersFomFile("src/main/resources/data/users.xml");
                users.getUsers().forEach(user -> userStorage.put("user:" + user.getId(), user));
                usersStored = true;
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }

        if (eventStorage != null && !eventsStored) {
            try {
                Events events = Mapper.mapEventsFomFile("src/main/resources/data/events.xml");
                events.getEvents().forEach(event -> eventStorage.put("event:" + event.getId(), event));
                eventsStored = true;
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }

        if (ticketStorage != null && !ticketStored) {
            try {
                Tickets tickets = Mapper.mapTicketsFromFile("src/main/resources/data/tickets.xml");
                tickets.getTickets().forEach(ticket -> ticketStorage.put("ticket:" + ticket.getId(), ticket));
                ticketStored = true;
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return bean;
    }
}
