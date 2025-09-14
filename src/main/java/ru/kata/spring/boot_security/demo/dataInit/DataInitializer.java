package ru.kata.spring.boot_security.demo.dataInit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {

            Role adminRole = new Role("ROLE_ADMIN");
            Role userRole = new Role("ROLE_USER");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            User admin = new User();
            admin.setName("admin");
            admin.setEmail("admin@mail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.addRole(adminRole);
            userRepository.save(admin);

            User user = new User();
            user.setName("user");
            user.setEmail("user@mail.com");
            user.setPassword(passwordEncoder.encode("user"));
            user.addRole(userRole);
            userRepository.save(user);
        }
    }
}
