package ru.SSidash.KataPP314.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.SSidash.KataPP314.dao.RoleDAO;
import ru.SSidash.KataPP314.dao.UserDAO;
import ru.SSidash.KataPP314.model.Role;
import ru.SSidash.KataPP314.model.User;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserService(UserDAO userDAO, RoleDAO roleDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            return null;
        }
        return user;
    }

    public User findById(Long userId) {
        Optional<User> userFromDb = userDAO.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public boolean saveUser(User user) {
        Set<Role> roles = user.getRoles();
        if (roles.size() == 0) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
        if (!user.getPassword().contains("$2a$12$")) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDAO.save(user);
        return true;
    }

    public boolean deleteById(Long userId) {
        if (userDAO.findById(userId).isPresent()) {
            userDAO.deleteById(userId);
            return true;
        }
        return false;
    }

}
