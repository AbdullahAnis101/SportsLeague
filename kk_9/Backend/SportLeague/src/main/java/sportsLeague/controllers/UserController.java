package sportsLeague.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sportsLeague.entity.User;
import sportsLeague.services.UserService;

@RestController
//@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
        }

    @RequestMapping("/user/get")
    public User getUserByName(@RequestBody String nametoGet) {
        User userGrabbed = userService.getUserByName(nametoGet);
        return userGrabbed;
    }
        
    
        //@RequestMapping(method = RequestMethod.POST, path = "/user/")
        @PostMapping("/user/add" )
        public void setName (@RequestBody User user) {
        	userService.addUser(user);
        }
        
        
        @RequestMapping(method = RequestMethod.DELETE, value = "/user")
        public void deleteUser(@PathVariable String userName) {
        	// userService.deleteUser(userName);
        }

        
    

}
