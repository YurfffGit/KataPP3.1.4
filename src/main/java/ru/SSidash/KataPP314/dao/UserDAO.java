package ru.SSidash.KataPP314.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.SSidash.KataPP314.model.User;


public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
