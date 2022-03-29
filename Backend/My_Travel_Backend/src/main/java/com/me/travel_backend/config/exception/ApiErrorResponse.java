package com.me.travel_backend.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {
    private HttpStatus status;

    private String message;

    private String detailMessage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    public ApiErrorResponse(HttpStatus status, String message, String detailMessage) {
        this.status = status;
        this.message = message;
        this.detailMessage = detailMessage;
    }
}


