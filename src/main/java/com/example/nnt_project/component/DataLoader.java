package com.example.nnt_project.component;

import com.example.nnt_project.repository.UserRepository;
import com.example.nnt_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.example.nnt_project.role.Role.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if ("always".equals(initMode)) {

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .firstname("Adminbek")
                        .lastname("Adminbekov")
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .role(ADMIN)
                        .build();
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("manager").isEmpty()) {
                User manager = User.builder()
                        .firstname("Manager")
                        .lastname("Manager")
                        .username("manager")
                        .password(passwordEncoder.encode("123"))
                        .role(MANAGER)
                        .build();
                userRepository.save(manager);
            }

            if (userRepository.findByUsername("dispatcher").isEmpty()) {
                User dispatcher = User.builder()
                        .firstname("Dispatch")
                        .lastname("Dispatchov")
                        .username("dispatcher")
                        .password(passwordEncoder.encode("123"))
                        .role(DISPATCHER)
                        .build();
                userRepository.save(dispatcher);
            }

//            if (userRepository.findByUsername("accountant").isEmpty()) {
//                User accountant = User.builder()
//                        .firstname("Account")
//                        .lastname("Accountov")
//                        .username("accountant")
//                        .password(passwordEncoder.encode("123"))
//                        .role(ACCOUNTANT)
//                        .build();
//                userRepository.save(accountant);
//            }
        }
    }
}
