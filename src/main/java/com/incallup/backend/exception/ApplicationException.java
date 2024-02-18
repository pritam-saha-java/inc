package com.incallup.backend.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class ApplicationException extends RuntimeException{

    private final Integer status;
    private final String title;
    private final String Description;

}
