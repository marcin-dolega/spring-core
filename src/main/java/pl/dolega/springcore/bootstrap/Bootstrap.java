package pl.dolega.springcore.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import pl.dolega.springcore.model.event.Event;
import pl.dolega.springcore.model.ticket.Ticket;
import pl.dolega.springcore.model.user.User;

import java.util.LinkedHashMap;

public class Bootstrap implements CommandLineRunner {

    @Autowired
    LinkedHashMap<String, User> userStorage;
    @Autowired
    LinkedHashMap<String, Event> eventStorage;
    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    @Override
    public void run(String... args) {

        System.out.println(userStorage);
        System.out.println(eventStorage);
        System.out.println(ticketStorage);

    }
}
