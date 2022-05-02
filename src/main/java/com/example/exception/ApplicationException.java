package com.example.exception;


/**
 *
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -3390871234726384042L;

    public enum Code {
        INVALID_INPUT(400),
        CONFLICT(409),
        UNAUTHORIZED(403),
        BACKEND_ERROR(500);

        private int statusCode;

        Code(int statusCode) {
            this.statusCode = statusCode;
        }

    }

    private final Code code;

    public ApplicationException(Code code, String message) {
        super(message);
        this.code = code;
    }
}
