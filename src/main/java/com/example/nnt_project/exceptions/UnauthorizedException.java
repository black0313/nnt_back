package com.example.nnt_project.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    private String type;
    private String message;

    public UnauthorizedException(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
