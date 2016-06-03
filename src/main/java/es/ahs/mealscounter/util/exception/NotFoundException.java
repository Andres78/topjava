package es.ahs.mealscounter.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Andrey Kuznetsov

 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No data found")  // 404
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
