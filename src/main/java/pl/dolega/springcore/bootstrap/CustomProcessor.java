package pl.dolega.springcore.bootstrap;

import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.PropertySource;
import pl.dolega.springcore.model.ticket.Ticket;
import pl.dolega.springcore.model.ticket.Tickets;
import pl.dolega.springcore.model.event.Event;
import pl.dolega.springcore.model.event.Events;
import pl.dolega.springcore.model.user.User;
import pl.dolega.springcore.model.user.Users;
import pl.dolega.springcore.utils.Mapper;

import java.util.LinkedHashMap;

@PropertySource("src/main/resources/dataSource.properties")
public class CustomProcessor implements BeanPostProcessor {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    @Value("${file.users}")
    private String usersFilePath;

    @Value("${file.events}")
    private String eventsFilePath;

    @Value("${file.tickets}")
    private String ticketsFilePath;

    boolean usersStored = false;
    boolean eventsStored = false;
    boolean ticketStored = false;

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (userStorage != null && !usersStored) {
            try {
                Users users = Mapper.mapUsersFomFile(usersFilePath);
                users.getUsers().forEach(user -> userStorage.put("user:" + user.getId(), user));
                usersStored = true;
                logger.info("Users stored");
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }

        if (eventStorage != null && !eventsStored) {
            try {
                Events events = Mapper.mapEventsFomFile(eventsFilePath);
                events.getEvents().forEach(event -> eventStorage.put("event:" + event.getId(), event));
                eventsStored = true;
                logger.info("Events stored");
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }

        if (ticketStorage != null && !ticketStored) {
            try {
                Tickets tickets = Mapper.mapTicketsFromFile(ticketsFilePath);
                tickets.getTickets().forEach(ticket -> ticketStorage.put("ticket:" + ticket.getId(), ticket));
                ticketStored = true;
                logger.info("Tickets stored");
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return bean;
    }
}
