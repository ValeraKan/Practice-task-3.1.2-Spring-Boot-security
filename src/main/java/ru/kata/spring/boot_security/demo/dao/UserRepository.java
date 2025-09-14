package ru.kata.spring.boot_security.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
}
