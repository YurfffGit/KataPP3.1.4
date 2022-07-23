package ru.SSidash.KataPP314.controller;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.SSidash.KataPP314.model.User;
import ru.SSidash.KataPP314.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("")
//    public List<User> getAllUsers(){
//        return userService.findAll();
//    }

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> allUsers = userService.findAll();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable long id) {
//        return userService.findById(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        try {
            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("")
//    public User addNewUser(@RequestBody User user) {
//        userService.saveUser(user);
//        return user;
//    }

    @PostMapping("")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

//    @PutMapping("")
//    public User updateUser(@RequestBody User user) {
//        userService.updateUser(user);
//        return user;
//    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable long id) {
//        userService.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
