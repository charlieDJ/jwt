package com.example.boot.exception;

import com.example.boot.common.enumeration.ResponseCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author dengjia
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CustomException extends RuntimeException {

    private String code;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ResponseCodes responseCodes) {
        super(responseCodes.getMsg());
        this.code = responseCodes.getCode();
    }


}
