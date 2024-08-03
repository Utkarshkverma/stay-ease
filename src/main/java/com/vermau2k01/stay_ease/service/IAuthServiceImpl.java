package com.vermau2k01.stay_ease.service;


import com.vermau2k01.stay_ease.entity.Roles;
import com.vermau2k01.stay_ease.entity.Users;
import com.vermau2k01.stay_ease.repository.RolesRepository;
import com.vermau2k01.stay_ease.repository.UsersRepository;
import com.vermau2k01.stay_ease.request.AuthenticationRequest;
import com.vermau2k01.stay_ease.request.RegistrationRequest;
import com.vermau2k01.stay_ease.response.AuthenticationResponse;
import com.vermau2k01.stay_ease.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IAuthServiceImpl implements IAuthService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public void register(RegistrationRequest request) {

        Roles userRole;

        if(request.getEmail().endsWith("stayease.admin.in"))
        {
            userRole = roleRepository.findByRole("ADMIN")
                    .orElseThrow(() ->
                            new IllegalStateException("Role ADMIN was not initalized"));
        }

        else if(request.getEmail().endsWith("stayease.manager.in"))
        {
            userRole = roleRepository.findByRole("MANAGER")
                    .orElseThrow(() ->
                            new IllegalStateException("Role MANAGER was not initialized"));
        }

        else {

            userRole = roleRepository.findByRole("USER")
                    .orElseThrow(() ->
                            new IllegalStateException("Role USER was not initialized"));
        }

        var user = Users
                .builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .passcode(passwordEncoder.encode(request.getPassword()))
                .role(List.of(userRole))
                .build();

        userRepository.save(user);

    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String,Object>();
        var user = ((Users)auth.getPrincipal());
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
