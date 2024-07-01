package com.shipmnts.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRes {
    Long id;
    String email;
    String fullName;
}
