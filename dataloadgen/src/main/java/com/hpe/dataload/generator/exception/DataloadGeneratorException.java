package com.hpe.dataload.generator.exception;

public class DataloadGeneratorException extends Exception {

    private static final long serialVersionUID = -1380953986806541205L;

    public DataloadGeneratorException(String message) {
        super(message);
    }

    public DataloadGeneratorException(Throwable cause) {
        super(cause);
    }
}
