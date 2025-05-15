package com.github.jinn9.member.exception;

public class DuplicateMemberException extends RuntimeException {

    public DuplicateMemberException(String message) {
        super(message);
    }

    public DuplicateMemberException(String message, Throwable cause) {
        super(message, cause);
    }
}
