package com.example.controllers;

import com.example.entities.User;
import com.example.services.FriendRepository;
import com.example.services.MessageRepository;
import com.example.services.UserRepository;
import com.example.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by ericweidman on 10/31/16.
 */

@RestController
public class CliqueController {


    @Autowired
    UserRepository users;
    @Autowired
    MessageRepository messages;
    @Autowired
    FriendRepository friends;


    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public User createUser(@RequestBody User newUser) throws Exception {

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

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody User loginAttempt, HttpSession session) {

        User user = users.findByUserName(loginAttempt.getUserName());


        session.setAttribute("userName", loginAttempt.getUserName());
        return "Authenticated";

    }


    @RequestMapping(path = "/edit-user", method = RequestMethod.PUT)
    public void editUser(String newUserName) {


    }

    @RequestMapping(path = "/delete-user{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) {
        users.delete(id);
    }

    @RequestMapping(path = "add-friend", method = RequestMethod.POST)
    public void addFriend(@PathVariable int id, Boolean friend, HttpSession session) {


    }

    @RequestMapping(path = "remove-friend", method = RequestMethod.POST)
    public void removeFriend(@PathVariable int id, Boolean friend, HttpSession session) {

    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();
    }

}
