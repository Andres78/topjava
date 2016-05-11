package ru.javawebinar.topjava.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ahs on 11.05.16.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Unprocessable entity")  // 404
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
