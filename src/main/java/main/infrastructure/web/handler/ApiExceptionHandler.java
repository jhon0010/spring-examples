package main.infrastructure.web.handler;

import main.infrastructure.web.exceptions.ApiRequestException;
import main.infrastructure.web.exceptions.RESTApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(
            value = {RESTApiRequestException.class}
    )
    public ResponseEntity<Object> handleApiRequestException(RESTApiRequestException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        var apiRequestException = new ApiRequestException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiRequestException, badRequest);

    }

}
