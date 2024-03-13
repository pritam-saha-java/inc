package com.incallup.backend.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.StandardException;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@Builder
@StandardException
public class AccountCreationException extends RuntimeException{


    private  Integer status;
    private  String title;
    private  String Description;
}
