package com.gersee.pairing.common;

/**
 * @author Marc Bober
 */
public class PairingException extends RuntimeException {

    public PairingException() {
        super();
    }

    public PairingException(String message) {
        super(message);
    }

    public PairingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PairingException(Throwable cause) {
        super(cause);
    }

    protected PairingException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
