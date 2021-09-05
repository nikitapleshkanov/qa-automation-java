package com.tinkoff.edu.app.exceptions;

public class RequestException extends IllegalArgumentException {

    public RequestException() {
        super();
    }

    public RequestException(String message) {
        super(message);
    }

    public RequestException(Throwable cause) {
        super(cause);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
