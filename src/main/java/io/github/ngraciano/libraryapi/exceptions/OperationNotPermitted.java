package io.github.ngraciano.libraryapi.exceptions;

public class OperationNotPermitted extends RuntimeException {

    public OperationNotPermitted(String msg) {
        super(msg);
    }
}
