package sportsLeague.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sportsLeague.entity.Store;
import sportsLeague.entity.User;
import sportsLeague.services.StoreService;
import sportsLeague.services.UserService;

@RestController
//@RequestMapping
public class StoreController {

    @Autowired
    StoreService SS;

    @RequestMapping("/store/get")
    public List<Store> getStoreByName(@RequestBody String nametoGet) {
        List<Store> UStore = SS.getStoreByName(nametoGet);
        return UStore;
    }



    //@RequestMapping(method = RequestMethod.POST, path = "/user/")
    @PostMapping("/store/add" )
    public void setName (@RequestBody Store store) {
        SS.addStoreItem(store);
    }





}
