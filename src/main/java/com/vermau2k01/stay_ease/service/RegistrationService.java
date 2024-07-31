package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.MyUsers;
import com.vermau2k01.stay_ease.repository.UserRepository;
import com.vermau2k01.stay_ease.request.LoginForm;
import com.vermau2k01.stay_ease.request.RegistrationRequest;
import com.vermau2k01.stay_ease.response.RegistrationResponse;
import com.vermau2k01.stay_ease.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;
    private MyUserDetailsService userDetailsService;


    @Override
    public MyUsers registerUser(RegistrationRequest registrationRequest) {

        MyUsers build = MyUsers.builder()
                .name(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(setRole(registrationRequest.getEmail())).build();



        return userRepository.save(build);
    }

    @Override
    public RegistrationResponse login(LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()
        ));
        if (authentication.isAuthenticated()) {
            return new RegistrationResponse("Hello");
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
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
