package main.shared.infrastructure.exception;

public class RESTApiRequestException extends RuntimeException {

    public RESTApiRequestException(String message){
        super(message);
    }

    public RESTApiRequestException(String message, Throwable e){
        super(message, e);
    }

}
