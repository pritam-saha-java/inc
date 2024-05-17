package com.incallup.backend.response;

import lombok.Data;

@Data
public class SellerUpdateProfileRequest {

    private String email;
    private String phoneNumber;
    private String password;
}
