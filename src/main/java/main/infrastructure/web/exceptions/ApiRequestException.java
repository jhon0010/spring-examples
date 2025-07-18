package main.infrastructure.web.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ApiRequestException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public ApiRequestException(String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }
}
