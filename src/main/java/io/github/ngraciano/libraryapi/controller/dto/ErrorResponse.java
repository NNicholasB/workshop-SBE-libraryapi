package io.github.ngraciano.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(int status, String msg, List<ErrorCamp> errors) {

    public  static ErrorResponse responseDefault(String msg){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), msg,List.of() );
    }
   public static ErrorResponse conflict(String msg){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), msg,List.of());
    }

}
