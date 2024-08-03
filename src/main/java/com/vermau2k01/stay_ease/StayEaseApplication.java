package com.vermau2k01.stay_ease;

import com.vermau2k01.stay_ease.entity.Roles;
import com.vermau2k01.stay_ease.repository.RolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StayEaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StayEaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RolesRepository roleRepository) {
		return args -> {
			if (roleRepository.findByRole("USER").isEmpty()) {
				roleRepository.save(Roles.builder().role("USER").build());
			}

			if (roleRepository.findByRole("ADMIN").isEmpty()) {
				roleRepository.save(Roles.builder().role("ADMIN").build());
			}

			if (roleRepository.findByRole("MANAGER").isEmpty()) {
				roleRepository.save(Roles.builder().role("MANAGER").build());
			}
		};
	}

}
