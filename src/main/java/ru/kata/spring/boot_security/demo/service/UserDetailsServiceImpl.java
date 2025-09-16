package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kata.spring.boot_security.demo.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Попытка загрузки пользователя с email: {}", email);
        User user = userService.getUserByEmail(email);
        if (user == null) {
            logger.error("Пользователь не найден с email: {}", email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        logger.info("Пользователь найден: ID={}, email={}, name={}, roles={}",
                user.getId(), user.getEmail(), user.getName(), user.getRoles());
        return user;
    }
}