package com.jwt.jwtsecurity;

import com.jwt.jwtsecurity.entities.Role;
import com.jwt.jwtsecurity.entities.User;
import com.jwt.jwtsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtSecurityApplication implements CommandLineRunner {

    @Autowired private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if (adminAccount == null) {
            User user = new User();

            user.setEmail("hamed.zarrabi87@gmail.com");
            user.setFirstName("Hamed");
            user.setLastName("Zarrabi");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("lightwave"));

            userRepository.save(user);
        }
    }
}
