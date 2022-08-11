package ru.SSidash.KataPP314;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.SSidash.KataPP314.model.Role;
import ru.SSidash.KataPP314.model.User;
import ru.SSidash.KataPP314.service.RoleService;
import ru.SSidash.KataPP314.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInit {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseInit(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void databaseInit() {
        if (roleService.findAll().isEmpty()) {
            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");
            roleService.saveRole(admin);
            roleService.saveRole(user);
            Set<Role> roles = new HashSet<>();
            roles.add(user);
            User simpleUser = new User("user", "user", (byte)32, "user");
            simpleUser.setRoles(roles);
            simpleUser.setPassword(passwordEncoder.encode("user"));
            roles = new HashSet<>();
            roles.add(user);
            roles.add(admin);
            User adminUser = new User("admin", "admin", (byte) 33, "admin");
            adminUser.setRoles(roles);
            adminUser.setPassword(passwordEncoder.encode("admin"));
            userService.saveUser(simpleUser);
            userService.saveUser(adminUser);
        }
    }
}
