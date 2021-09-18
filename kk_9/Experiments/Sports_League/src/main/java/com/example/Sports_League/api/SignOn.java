package com.example.Sports_League.api;

import com.example.Sports_League.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/userInfo")
@RestController
public class SignOn {

    private final UserService UserService;

    @Autowired
    public SignOn(com.example.Sports_League.service.UserService US){
        UserService = US;
    }

    @GetMapping(path = "{username}")
    public Boolean SignIn( @PathVariable String username){
        return UserService.SignIn(username);
    }

}
