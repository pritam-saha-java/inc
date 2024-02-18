package com.incallup.backend.utility;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseStructure<T> {

    private String message;
    private int status;
    private T data;

}
