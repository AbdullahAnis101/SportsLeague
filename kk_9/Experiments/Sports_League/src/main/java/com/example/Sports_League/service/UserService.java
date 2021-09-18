package com.example.Sports_League.service;

import com.example.Sports_League.dao.UserDao;
import com.example.Sports_League.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao UD){
        userDao = UD;
    }



    public int addUser(User user){
        return userDao.insertUser(user);

    }

    public List<User> getAllUsers(){
        return userDao.selectAllUsers();
    }


    public Optional<User> getUserById(UUID id){

        return userDao.selectUserByID(id);
    }

    public Boolean SignIn(String Login){
        return userDao.SignIn(Login);
    }

    public int deleteUser(UUID id){
        return userDao.deleteUserById(id);
    }

    public int updateUser(UUID id,User user){
        return userDao.updateUserById(id,user);
    }
}

