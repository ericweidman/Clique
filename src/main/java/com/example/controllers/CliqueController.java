package com.example.controllers;

import com.example.entities.User;
import com.example.services.UserRepository;
import com.example.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ericweidman on 10/31/16.
 */

@RestController
public class CliqueController {

    @Autowired
    UserRepository users;


    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public String createUser(@RequestBody User newUser) throws Exception {

        if (newUser.getUserName() == null) {
            throw new Exception("Username cannot be blank");
        }

        if (newUser.getPassword() == null) {
            throw new Exception("Password cannot be blank");
        }

        User user = users.findByUserName(newUser.getUserName());
        if (user == null) {
            user = new User(newUser.getUserName(), PasswordStorage.createHash(newUser.getPassword()));
            users.save(user);
            return null;
        } else {
            throw new Exception("Username already taken");
        }
    }

    @RequestMapping(path = "/edit-user", method = RequestMethod.PUT)
    public String editUser(String newUserName) {

        return null;

    }

    @RequestMapping(path = "/delete_user", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) {
        users.delete(id);
    }

    


}
