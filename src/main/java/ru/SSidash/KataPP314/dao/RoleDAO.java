package ru.SSidash.KataPP314.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.SSidash.KataPP314.model.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
