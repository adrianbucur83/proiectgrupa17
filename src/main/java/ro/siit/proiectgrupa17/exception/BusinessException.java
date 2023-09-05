package ro.siit.proiectgrupa17.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    private int statusCode;
}

