package com.shipmnts.backend.requests;

import lombok.Data;

@Data
public class RegisterUserReq {
    String fullName;
    String email;
    String password;
}
