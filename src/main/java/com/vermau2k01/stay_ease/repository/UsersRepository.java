package com.vermau2k01.stay_ease.repository;

import com.vermau2k01.stay_ease.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

}
