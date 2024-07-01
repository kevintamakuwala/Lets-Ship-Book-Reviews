package com.shipmnts.backend.requests;

import lombok.Data;

@Data
public class LoginUserReq {
    String email;
    String password;
}
