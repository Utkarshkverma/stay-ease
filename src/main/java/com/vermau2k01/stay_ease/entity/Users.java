package com.vermau2k01.stay_ease.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.security.Principal;
import java.util.ArrayList;
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
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @NaturalId(mutable = true)
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String passcode;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<RoomBooking> roomBooking = new ArrayList<>();

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
