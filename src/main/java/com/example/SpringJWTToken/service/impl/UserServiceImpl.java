package com.example.SpringJWTToken.service.impl;

import com.example.SpringJWTToken.exeption.user.UserNotfoundException;
import com.example.SpringJWTToken.model.Role;
import com.example.SpringJWTToken.model.Status;
import com.example.SpringJWTToken.model.User;
import com.example.SpringJWTToken.repository.RoleRepository;
import com.example.SpringJWTToken.repository.UserRepository;
import com.example.SpringJWTToken.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(@Lazy UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User register(User user) {
        Role roleUser =  roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registerUser = userRepository.save(user);
        log.info("Register - user {} : successfully register", registerUser);
        return registerUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("All user: {}", result);
        return result;
    }

    @Override
    public User findByUsername(String name) {
        User userByName = userRepository.findByUsername(name);
        log.info("User: {} found by {}",name, userByName);
        return userByName;
    }

    @Override
    public User findById(Long id) {
        User userById = userRepository.findById(id).orElse(null);

        if (userById == null){
            log.info("User with id {} not found",id);
            throw new UserNotfoundException();
        }
        log.info("User: {} found by {}", userById, id);
        return userById;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("User with id {} was delete", id);
    }
}
