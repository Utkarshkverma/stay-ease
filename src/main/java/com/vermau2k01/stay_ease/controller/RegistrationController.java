package com.vermau2k01.stay_ease.controller;


import com.vermau2k01.stay_ease.entity.MyUsers;
import com.vermau2k01.stay_ease.request.LoginForm;
import com.vermau2k01.stay_ease.request.RegistrationRequest;
import com.vermau2k01.stay_ease.response.RegistrationResponse;
import com.vermau2k01.stay_ease.security.JwtAuthenticationFilter;
import com.vermau2k01.stay_ease.security.JwtService;
import com.vermau2k01.stay_ease.service.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final IRegistrationService registrationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public ResponseEntity<MyUsers> register
            (@RequestBody RegistrationRequest registrationRequest){
        MyUsers myUsers = registrationService
                .registerUser(registrationRequest);

        return new ResponseEntity<>(myUsers, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<RegistrationResponse> login(@RequestBody LoginForm login){
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.username(),
                        login.password()
                )
        );
        var users = ((MyUsers)auth.getPrincipal());
        var jwtToken = jwtService.generateToken((UserDetails) users);
        RegistrationResponse build = RegistrationResponse.builder().token(jwtToken).build();
        return new ResponseEntity<>(build, HttpStatus.OK);
    }
}
