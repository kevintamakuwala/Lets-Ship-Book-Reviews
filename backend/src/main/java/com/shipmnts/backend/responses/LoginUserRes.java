package com.shipmnts.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserRes {
    private String token;

    private long expiresIn;

}
