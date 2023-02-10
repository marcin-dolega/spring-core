package pl.dolega.springcore.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.Ticket;
import pl.dolega.springcore.model.User;

import java.util.LinkedHashMap;

public class DataInitializer implements CommandLineRunner {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    @Autowired
    LinkedHashMap<String, Ticket> ticketStorage;

    @Override
    public void run(String... args) {

        System.out.println(userStorage.values());
        System.out.println(eventStorage.values());
        System.out.println(ticketStorage.values());

    }

}
