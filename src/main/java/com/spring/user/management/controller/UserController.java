package com.spring.user.management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.user.management.dao.UserDAOImpl;
import com.spring.user.management.dto.UserResponseDto;
import com.spring.user.management.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserDAOImpl userDAO;

    @GetMapping("/all")
    @ResponseBody
    public List<User> getAllUsers(){

        return userDAO.getAll();
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public User getUserById(@PathVariable int userId){

        return userDAO.getOne(userId);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<UserResponseDto> addUser(@RequestBody User user){
        UserResponseDto objUserResponseDto = userDAO.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(objUserResponseDto);
    }

    @PutMapping("/{userId}")
    @ResponseBody
    public String modifyUser(@PathVariable int userId , @RequestBody User user) throws JsonProcessingException {
        return userDAO.modifyUser(user , userId)+" user modified";
    }

    @DeleteMapping("/{userId}")
    @ResponseBody
    public String deleteUser(@PathVariable int userId){

        return userDAO.deleteUser(userId)+" user deleted";
    }
}
