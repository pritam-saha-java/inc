package com.incallup.backend.exception;

import lombok.*;
import lombok.experimental.StandardException;


@Getter
@Setter
@ToString
@Builder
@StandardException
public class ApplicationException extends Exception{

    private  Integer status;
    private  String title;
    private  String Description;

}
