package ru.SSidash.KataPP314.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.SSidash.KataPP314.model.User;
import ru.SSidash.KataPP314.service.RoleService;
import ru.SSidash.KataPP314.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", user);
        model.addAttribute("new_user", new User());
        return "admin";
    }

    @PostMapping("/new")
    public String addUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String deleteUserById(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
