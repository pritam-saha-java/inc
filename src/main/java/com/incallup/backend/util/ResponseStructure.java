package com.incallup.backend.util;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseStructure<T> {

    private String message;
    private int status;
    private T data;

}
