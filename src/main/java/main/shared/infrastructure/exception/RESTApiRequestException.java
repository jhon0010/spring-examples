package main.shared.infrastructure.exception;

import java.text.MessageFormat;

public class RESTApiRequestException extends RuntimeException {

    public RESTApiRequestException(String message){
        super(message);
    }

    public RESTApiRequestException(String message, Throwable e){
        super(message, e);
    }

    public static RESTApiRequestException createNoCustomerByIdException(Long id, Throwable e){
        return new RESTApiRequestException(
                MessageFormat.format("There is not customer with id {0}", id), e
        );
    }

}
