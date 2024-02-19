package com.incallup.backend.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.StandardException;

@Getter
@Setter
@ToString
@Builder
@StandardException
public class IdNotFoundException extends RuntimeException{
    private  Integer id;
    private  String entity;
    private  String message;


}
