package pl.dolega.springcore.mapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.model.User;
import pl.dolega.springcore.model.Users;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.util.LinkedHashMap;

public class Mapper {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    private void jaxbXmlFileToObjectList(String fileName) throws JAXBException, XMLStreamException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Users users = (Users) jaxbUnmarshaller.unmarshal(new File(fileName));

        for (User user : users.getUsers()) {
            userStorage.put("user:" + user.getId(), user);
        }

        System.out.println(userStorage);
    }
}
