package ru.SSidash.KataPP314.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.SSidash.KataPP314.dao.UserDAO;
import ru.SSidash.KataPP314.model.Role;
import ru.SSidash.KataPP314.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDAO.findByEmail(email);
    }

    public User findById(Long userId) {
        return userDAO.findById(userId).orElse(null);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        checkRolesAndPass(user);
        userDAO.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        checkRolesAndPass(user);
        userDAO.saveAndFlush(user);
    }

    private void checkRolesAndPass(User user) {
        Set<Role> roles = user.getRoles();
        if (roles.size() == 0) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
        if (!user.getPassword().contains("$2a$12$")) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
    }

    @Transactional
    public void deleteById(Long userId) {
        userDAO.deleteById(userId);
    }

}
