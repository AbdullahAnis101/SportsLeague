package com.example.Sports_League.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Sports_League.model.User;


public interface UserDao {
    int insertUser(UUID id, User user);

    default int insertUser(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id,user);
    }

    List<User> selectAllUsers();

    Optional<User> selectUserByID(UUID id);

    Boolean SignIn(String Login);

    int deleteUserById(UUID id);

    int updateUserById(UUID id,User user);


}
