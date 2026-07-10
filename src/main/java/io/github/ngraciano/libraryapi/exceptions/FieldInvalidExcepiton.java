package io.github.ngraciano.libraryapi.exceptions;

import lombok.Getter;

public class FieldInvalidExcepiton extends RuntimeException {

    @Getter
    private String field;

    public FieldInvalidExcepiton(String field, String msg){
        super(msg);
        this.field=field;
    }
}
