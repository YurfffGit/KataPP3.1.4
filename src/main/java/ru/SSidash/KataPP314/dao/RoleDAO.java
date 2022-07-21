package ru.SSidash.KataPP314.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.SSidash.KataPP314.model.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
