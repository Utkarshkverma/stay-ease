package com.vermau2k01.stay_ease.controller;


import com.vermau2k01.stay_ease.entity.MyUsers;
import com.vermau2k01.stay_ease.request.RegistrationRequest;
import com.vermau2k01.stay_ease.service.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final IRegistrationService registrationService;

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public ResponseEntity<MyUsers> register
            (@RequestBody RegistrationRequest registrationRequest){
        MyUsers myUsers = registrationService
                .registerUser(registrationRequest);

        return new ResponseEntity<>(myUsers, HttpStatus.CREATED);
    }
}
