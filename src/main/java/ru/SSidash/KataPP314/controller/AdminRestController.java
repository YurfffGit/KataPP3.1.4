package ru.SSidash.KataPP314.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.SSidash.KataPP314.model.Role;
import ru.SSidash.KataPP314.model.User;
import ru.SSidash.KataPP314.service.RoleService;
import ru.SSidash.KataPP314.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.findAll();
//        return users != null && !users.isEmpty()
//                ? new ResponseEntity<>(users, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return users != null && !users.isEmpty()
                ? ResponseEntity.ok(users)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id) {
        User user = userService.findById(id);
//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
        return ResponseEntity.status(201).body(user);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
//        return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/viewUser")
    public ResponseEntity<User> showUser(Authentication auth) {
//        return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
        return ResponseEntity.ok((User) auth.getPrincipal());
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<Role>> getAllRoles() {
//        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
//        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
        return ResponseEntity.ok(roleService.findById(id));
    }
}


