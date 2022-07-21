package ru.SSidash.KataPP314.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.SSidash.KataPP314.model.User;
import ru.SSidash.KataPP314.service.RoleService;
import ru.SSidash.KataPP314.service.UserService;


@Controller
@RequestMapping("/user")
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/{id}")
    public String userPage(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "user";
    }




}
