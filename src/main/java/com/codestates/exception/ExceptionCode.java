package com.codestates.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found");

    @Getter
    private final int status;

    @Getter
    private final String message;
    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }

}
