package pl.dolega.springcore.utils;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.UserDao;
import pl.dolega.springcore.dao.impl.UserDaoImpl;
import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.exceptions.RecordAlreadyExistException;
import pl.dolega.springcore.model.user.User;

import java.util.LinkedHashMap;

public class RecordChecker {

    @Autowired
    UserDao userDao;

    public boolean doesExist(String entity, long id, LinkedHashMap<String, ?> storage) {

        if (checkCondition(entity, id, storage)) {
            try {
                throw new RecordAlreadyExistException(entity + ":" + id + " already exist");
            } catch (RecordAlreadyExistException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    public boolean doesExist(String entity, String email, LinkedHashMap<String, ?> storage) {

        if (checkCondition(entity, email, storage)) {
            try {
                throw new RecordAlreadyExistException(entity + ":" + email + " already exist");
            } catch (RecordAlreadyExistException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    public boolean doesntExist(String entity, long id, LinkedHashMap<String, ?> storage) {
        if (checkCondition(entity, id, storage)) {
            return false;
        }
        try {
            throw new NoSuchRecordException(entity + " of id: " + id + " doesn't exist");
        } catch (NoSuchRecordException e) {
            e.printStackTrace();
            return true;
        }

    }

    private boolean checkCondition(String entity, long id, LinkedHashMap<String, ?> storage) {
        return storage.get(entity + ":" + id) != null;
    }

    private boolean checkCondition(String entity, String email, LinkedHashMap<String, ?> storage) {
        return storage.get(entity + ":" + 2) != null;
    }
}
