package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.MyUsers;
import com.vermau2k01.stay_ease.repository.UserRepository;
import com.vermau2k01.stay_ease.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public MyUsers registerUser(RegistrationRequest registrationRequest) {

        MyUsers build = MyUsers.builder()
                .name(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(setRole(registrationRequest.getEmail())).build();

        return userRepository.save(build);
    }


    private String setRole(String email)
    {
        if(email.endsWith("@stayease.manager.in"))
            return "MANAGER";
        else if(email.endsWith("@stayease.admin.in"))
            return "ADMIN";
        return "USER";
    }
}
