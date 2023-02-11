package pl.dolega.springcore.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import pl.dolega.springcore.model.event.Events;
import pl.dolega.springcore.model.ticket.Tickets;
import pl.dolega.springcore.model.user.Users;

import java.io.File;

public class Mapper {

    public static Users mapUsersFomFile(String fileName) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (Users) jaxbUnmarshaller.unmarshal(new File(fileName));
    }

    public static Events mapEventsFomFile(String fileName) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Events.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (Events) jaxbUnmarshaller.unmarshal(new File(fileName));
    }

    public static Tickets mapTicketsFromFile(String fileName) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Tickets.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (Tickets) jaxbUnmarshaller.unmarshal(new File(fileName));
    }
}

