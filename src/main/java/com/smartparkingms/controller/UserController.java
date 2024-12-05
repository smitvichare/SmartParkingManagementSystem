package com.smartparkingms.controller;

import com.smartparkingms.dto.UserReqDTO;
import com.smartparkingms.dto.UserRespDTO;
import com.smartparkingms.model.User;
import com.smartparkingms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/add")
    public UserRespDTO addUser(@Valid @RequestBody UserReqDTO userReqDTO){
        return userService.addUser(userReqDTO);
    }

    @GetMapping("/{id}")
    public UserRespDTO getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<UserRespDTO> getAllUser(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void delUser(@PathVariable long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserRespDTO updateUser(@PathVariable long id,@RequestBody UserReqDTO userReqDTO){
        return userService.updateUser(id,userReqDTO);
    }


}
