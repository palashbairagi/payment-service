package com.payment.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class HttpErrorResponse extends BaseModel {

    private HttpStatus httpStatus;
    private ErrorDto errorDto;

}
