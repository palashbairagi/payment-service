package com.payment.model.common;

import com.payment.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto extends Dto {

    private ErrorCode errorCode;

    private String code;

    private String message;

    public ErrorDto(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
