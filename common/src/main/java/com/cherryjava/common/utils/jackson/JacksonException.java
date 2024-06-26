package com.cherryjava.common.utils.jackson;

/**
 * @author hjamm
 */
public class JacksonException extends RuntimeException {

    public JacksonException(String message, Exception e) {
        super(message, e);
    }

}
