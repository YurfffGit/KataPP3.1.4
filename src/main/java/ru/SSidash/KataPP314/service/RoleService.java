package ru.SSidash.KataPP314.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Set<Role> findAll() {
        return new HashSet<>(roleDAO.findAll());
    }
    public Role findById(long id) {
        return roleDAO.findById(id).get();
    }

    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }

    public boolean save(Role role) {
        Role roleFromDB = roleDAO.findByName(role.getName());
        if (roleFromDB != null) {
            return false;
        }
        roleDAO.save(role);
        return true;
    }
}