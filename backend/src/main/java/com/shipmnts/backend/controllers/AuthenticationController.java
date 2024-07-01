package com.shipmnts.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipmnts.backend.entities.User;
import com.shipmnts.backend.requests.LoginUserReq;
import com.shipmnts.backend.requests.RegisterUserReq;
import com.shipmnts.backend.responses.LoginUserRes;
import com.shipmnts.backend.responses.UserRes;
import com.shipmnts.backend.services.AuthenticationService;
import com.shipmnts.backend.services.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRes> register(@RequestBody RegisterUserReq registerUserReq) {

        User registeredUser = authenticationService.signup(registerUserReq);

        UserRes userResponse = new UserRes(registeredUser.getId(), registeredUser.getEmail(),
                registeredUser.getFullName());

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserRes> authenticate(@RequestBody LoginUserReq loginUserReq) {
        User authenticatedUser = authenticationService.authenticate(loginUserReq);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        System.out.println(jwtToken+"%%%%%%%%%%%");
        LoginUserRes loginResponse = new LoginUserRes(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
