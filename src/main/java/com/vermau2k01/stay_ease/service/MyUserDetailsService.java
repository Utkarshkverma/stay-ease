package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.MyUsers;
import com.vermau2k01.stay_ease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail)
            throws UsernameNotFoundException {

        MyUsers myUsers = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No user found with username: " + userEmail));

        return User.builder()
                .username(myUsers.getEmail())
                .password(myUsers.getPassword())
                .roles(getRoles(myUsers))
                .build();
    }

    private String[] getRoles(MyUsers myUsers) {
        if(myUsers.getRole()==null)
            return new String[]{"USER"};
        else
            return myUsers.getRole().split(",");
    }
}
