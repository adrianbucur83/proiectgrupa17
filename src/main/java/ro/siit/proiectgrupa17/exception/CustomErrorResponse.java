package ro.siit.proiectgrupa17.exception;

import lombok.Data;

@Data
public class CustomErrorResponse {
    private int status;
    private String cause;
}
