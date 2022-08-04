package ru.SSidash.KataPP314;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.SSidash.KataPP314.model.Role;
import ru.SSidash.KataPP314.model.User;
import ru.SSidash.KataPP314.service.RoleService;
import ru.SSidash.KataPP314.service.UserService;

@Component
public class DatabaseInit {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DatabaseInit(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void databaseInit() {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        roleService.saveRole(admin);
        roleService.saveRole(user);

        User adminUser = new User();
    }
}
