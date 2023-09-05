package ro.siit.proiectgrupa17.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;



@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CustomErrorResponse> handleBusinessException(BusinessException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setStatus(ex.getStatusCode());
        errorResponse.setCause(ex.getCause() != null ? ex.getCause().toString() : "Unknown Cause");

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

}
