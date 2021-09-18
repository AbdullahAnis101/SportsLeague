package com.example.Sports_League.dao;

import com.example.Sports_League.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("fakeDao")
public class FakeUserDataAccessService implements UserDao {
    private static List<User> UserTable = new ArrayList<>();
    @Override
    public int insertUser(UUID id, User user) {
        UserTable.add(new User(user.getUsername(),id,user.getPassword()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return UserTable;
    }

    @Override
    public Optional<User> selectUserByID(UUID id) {
        return UserTable.stream()
                .filter(user -> user.getUser_id().equals(id))
                .findFirst();
    }

    @Override
    public Boolean SignIn(String Login) {
        String[] temp = Login.split("`");
        String UN = temp[0];
        String PW = temp[1];
        User Find;
        int i = 0;
        while(i < UserTable.size()){
            if(UserTable.get(i).getUsername().equals(UN) && UserTable.get(i).getPassword().equals(PW)) {
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public int deleteUserById(UUID id) {
       Optional<User> userMaybe =  selectUserByID(id);
       if(userMaybe.isEmpty()) {
           return 0;
       }
        UserTable.remove(userMaybe.get());
       return 1;
    }

    @Override
    public int updateUserById(UUID id, User user) {
        return selectUserByID(id)
                .map(p -> {
                    int indexOfUserToDelete = UserTable.indexOf(user);
                    if(indexOfUserToDelete >= 0){
                        UserTable.set(indexOfUserToDelete,user);
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }
}