package com.lks.core;

public class MRVException extends RuntimeException {

    private String message;
    private MRVErrorCodes errorCodes;

    public MRVException(MRVErrorCodes errorCodes,String message) {
        super(message);
        this.message = message;
        this.errorCodes = errorCodes;
    }

    public MRVException(MRVErrorCodes masErrorCodes, String message, Throwable cause) {

        super(message, cause);
        this.message = message;
        this.errorCodes = masErrorCodes;
    }

    public MRVException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MRVErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(MRVErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }
}
