package com.payment.enums;

public enum ErrorCode {

    GENERAL_INPUT_ERROR,
    REQUEST_MALFORMED,
    SERVER_ERROR;

    public String getKey() {
        return this.name();
    }

}
