package pl.dolega.springcore.exceptions;


public class NoSuchRecordException extends Exception {

    public NoSuchRecordException(String errorMessage) {
        super(errorMessage);
    }

}
