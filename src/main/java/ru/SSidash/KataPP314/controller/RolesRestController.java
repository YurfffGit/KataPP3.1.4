package ru.SSidash.KataPP314.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.SSidash.KataPP314.model.Role;
import ru.SSidash.KataPP314.service.RoleService;

import java.util.Set;

@RestController
public class RolesRestController {

    private final RoleService roleService;

    @Autowired
    public RolesRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/api/roles")
    public Set<Role> roleSet() {
        return roleService.getAllRoles();
    }

}
