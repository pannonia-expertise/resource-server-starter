package com.pannoniaexpertise.resourceserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceServerRoleException extends RuntimeException {

    public ResourceServerRoleException(final String message) {
        super(message);
    }
}
