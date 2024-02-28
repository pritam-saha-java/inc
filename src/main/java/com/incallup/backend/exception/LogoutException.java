package com.incallup.backend.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.StandardException;
import org.springframework.web.servlet.ModelAndView;

@Getter
@Setter
@ToString
@Builder
@StandardException
public class LogoutException extends RuntimeException{

    private  Integer status;
    private  String title;
    private  String Description;
    private ModelAndView modelAndView;
}
