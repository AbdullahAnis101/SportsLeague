/**
 *
 */
package sportsLeague.services;

import org.springframework.stereotype.Service;

import sportsLeague.entity.Store;
import sportsLeague.entity.User;
import sportsLeague.repo.StoreRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import sportsLeague.repo.UserRepository;

/**
 * @author
 * Richard Gonzalez
 * Jacob Kinser
 *
 *
 */

@Service
public class StoreService {

    @Autowired
    public StoreRepository StoreRepo;
  //  public UserRepository userRepo;




    public void addStoreItem(Store storeitem) {
        StoreRepo.save(new Store(0,storeitem.getItem(),storeitem.getUsername()));
    }


    public List<Store> getStoreByName(String nametoGet) {
            //System.out.println(nametoGet);
            List<Store> returnList = new ArrayList<>();
            List<Store> allStore = new ArrayList<>();
            StoreRepo.findAll().forEach(allStore::add);
//            ArrayList<User> user = new ArrayList<>();
//            userRepo.findAll().forEach(user::add);
            User ReturnUser;
            for(int i = 0; i < allStore.size();i++){
                if(allStore.get(i).getUsername().equals(nametoGet)){
                    //System.out.println(user.get(i).getName());
                   returnList.add(allStore.get(i));
                }
            }
//         for(int i = 0; i < user.size();i++){
//            if(user.get(i).getUsername().equals(nametoGet)){
//                //System.out.println(user.get(i).getName());
//                ReturnUser = user.get(i);
//                i = user.size();
//            }
//        }

            return returnList;

    }
}