package co.jobgem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SavedJobNotFoundException extends RuntimeException {
    public SavedJobNotFoundException(String message) {
        super(message);
    }
}
