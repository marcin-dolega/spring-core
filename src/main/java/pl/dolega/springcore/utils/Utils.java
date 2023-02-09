package pl.dolega.springcore.utils;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.exceptions.RecordAlreadyExistException;
import pl.dolega.springcore.model.User;

import java.util.LinkedHashMap;

public class Utils {

    @Autowired
    LinkedHashMap<String, User> userStorage;

    @Autowired
    LinkedHashMap<String, User> eventStorage;

    @Autowired
    LinkedHashMap<String, User> ticketStorage;

    public boolean doesExist(String entity, long id) {

        if (checkCondition(entity, id)) {
            try {
                throw new RecordAlreadyExistException(entity + ":" + id + " already exist");
            } catch (RecordAlreadyExistException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    public boolean doesntExist(String entity, long id) {
        if (checkCondition(entity, id)) {
            return false;
        }
        try {
            throw new NoSuchRecordException(entity + ":" + id + " doesn't exist");
        } catch (NoSuchRecordException e) {
            e.printStackTrace();
            return true;
        }

    }

    private boolean checkCondition(String entity, long id) {

        return switch (entity) {
            case "user" -> userStorage.get(entity + ":" + id) != null;
            case "event" -> eventStorage.get(entity + ":" + id) != null;
            case "ticket" -> ticketStorage.get(entity + ":" + id) != null;
            default -> false;
        };
    }
}
