package pl.dolega.springcore.exceptions;

public class RecordAlreadyExistException extends Exception {

    public RecordAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
