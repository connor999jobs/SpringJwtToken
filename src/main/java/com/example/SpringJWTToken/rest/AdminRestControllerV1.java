package com.example.SpringJWTToken.rest;


import com.example.SpringJWTToken.dto.AdminUserDto;
import com.example.SpringJWTToken.dto.UserDto;
import com.example.SpringJWTToken.exeption.user.UserNotfoundException;
import com.example.SpringJWTToken.model.User;
import com.example.SpringJWTToken.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.nativex.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminRestControllerV1 {


    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if (user == null){
            throw new UserNotfoundException();
        }
        AdminUserDto result = AdminUserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @SneakyThrows
    public ResponseEntity<UserDto> registerNew(@RequestBody User user){
        User createUser = userService.register(user);
        UserDto register = UserDto.fromUser(createUser);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable(name = "id") Long id) {
        try{
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }
}
