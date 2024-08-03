package com.vermau2k01.stay_ease.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Users implements UserDetails, Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String passcode;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> role;

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this
                .role
                .stream()
                .map(r->new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return passcode;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFullName()
    {
        return firstName +" "+lastName;
    }
}
