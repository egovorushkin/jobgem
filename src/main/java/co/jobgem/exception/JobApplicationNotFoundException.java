package co.jobgem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class JobApplicationNotFoundException extends RuntimeException {
    public JobApplicationNotFoundException(String message) {
        super(message);
    }
}
