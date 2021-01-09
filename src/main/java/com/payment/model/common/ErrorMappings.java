package com.payment.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMappings extends BaseModel {

    private List<ErrorMapping> errorMappings;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorMapping extends BaseModel {

        private String key;
        private int httpStatus;
        private String code;
        private String message;

        public ErrorMapping(String key, int status) {
            this.key = key;
            this.httpStatus = status;
        }

    }

}
