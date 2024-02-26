package com.incallup.backend.helper;

import com.incallup.backend.domain.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Admin admin;
    private String jwt;
}
