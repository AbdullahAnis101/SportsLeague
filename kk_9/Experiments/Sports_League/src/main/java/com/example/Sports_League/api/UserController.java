package com.example.Sports_League.api;

import com.example.Sports_League.model.User;
import com.example.Sports_League.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService UserService;

    @Autowired
    public UserController(com.example.Sports_League.service.UserService US){
        UserService = US;
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        UserService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return UserService.getAllUsers();
    }

   @GetMapping(path = "{id}")
    public User getUserById(@PathVariable UUID id){
        return UserService.getUserById(id).orElse(null);
    }


    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
        UserService.deleteUser(id);
    }

    @PutMapping
    public void updateUser(@PathVariable("id") UUID id,@RequestBody User userToUpdate){
        UserService.updateUser(id,userToUpdate);
    }
}