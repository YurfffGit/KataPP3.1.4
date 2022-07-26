package ru.SSidash.KataPP314.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
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
        checkRoles(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
//        if (!userDAO.findByEmail(user.getEmail()).getPassword().contains(user.getPassword())) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
        if (!userDAO.findById(user.getId()).get().getPassword().contains(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
    private void checkRoles(User user) {
        Set<Role> roles = user.getRoles();
        if (roles.size() == 0) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
    }

    @Transactional
    public void deleteById(Long userId) {
        userDAO.deleteById(userId);
    }

}
