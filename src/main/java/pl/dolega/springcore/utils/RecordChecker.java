package pl.dolega.springcore.utils;

import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.exceptions.RecordAlreadyExistException;

import java.util.LinkedHashMap;

public class RecordChecker {

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

    public boolean doesntExist(String entity, long id, LinkedHashMap<String, ?> storage) {
        if (checkCondition(entity, id, storage)) {
            return false;
        }
        try {
            throw new NoSuchRecordException(entity + ":" + id + " doesn't exist");
        } catch (NoSuchRecordException e) {
            e.printStackTrace();
            return true;
        }

    }

    private boolean checkCondition(String entity, long id, LinkedHashMap<String, ?> storage) {
        return storage.get(entity + ":" + id) != null;
    }
}
