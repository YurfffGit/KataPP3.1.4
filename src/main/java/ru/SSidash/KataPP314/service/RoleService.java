package ru.SSidash.KataPP314.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.SSidash.KataPP314.dao.RoleDAO;
import ru.SSidash.KataPP314.model.Role;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Set<Role> getAllRoles() {
        return new HashSet<>(roleDAO.findAll());
    }

    public Role getRole(String role) {
        return roleDAO.findByName(role);
    }
}