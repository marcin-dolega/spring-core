package pl.dolega.springcore.bootstrap;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import pl.dolega.springcore.model.User;
import pl.dolega.springcore.model.Users;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.util.LinkedHashMap;

public class DataInitializer implements CommandLineRunner {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    @Override
    public void run(String... args) throws Exception {

        jaxbXmlFileToObjectList("src/main/resources/data/users.xml");

    }

    private void jaxbXmlFileToObjectList(String fileName) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Users users = (Users) jaxbUnmarshaller.unmarshal(new File(fileName));

        for (User user : users.getUsers()) {
            userStorage.put("user:" + user.getId(), user);
        }

        System.out.println(userStorage);
    }
}
