package ru.SSidash.KataPP314.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.SSidash.KataPP314.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
