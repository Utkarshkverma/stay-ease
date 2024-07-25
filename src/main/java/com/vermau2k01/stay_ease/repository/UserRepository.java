package com.vermau2k01.stay_ease.repository;

import com.vermau2k01.stay_ease.entity.MyUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<MyUsers, UUID> {

    Optional<MyUsers> findByEmail(String userEmail);
}
