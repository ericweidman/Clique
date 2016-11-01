package com.example.controllers;

import com.example.entities.Message;
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


    //////////////////////
    //USER ACCOUNT ROUTES
    /////////////////////

    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public String createUser(@RequestBody User newUser, HttpSession session) throws Exception {

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
            session.setAttribute("userName", newUser.getUserName());
            return "redirect:/home.html";
        } else {
            throw new Exception("Username already taken");
        }
    }

    @RequestMapping(path = "/edit-user{id}", method = RequestMethod.PUT)
    public void editUser(@PathVariable("id") int id, String newUserName) {
        //Needs to accept any and all personal information changes.

    }

    @RequestMapping(path = "/delete-user{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) {
        users.delete(id);

    }

    //////////////////////
    //LOGIN/LOGOUT ROUTES
    /////////////////////

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody User loginAttempt, HttpSession session) {

        User user = users.findByUserName(loginAttempt.getUserName());

        session.setAttribute("userName", loginAttempt.getUserName());
        return "redirect:/home.html";

    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();

    }

    ////////////////
    //FRIEND ROUTES
    ///////////////

    @RequestMapping(path = "/add-friend{id}", method = RequestMethod.POST)
    public void addFriend(@PathVariable("id") int id, Boolean friend, HttpSession session) {

    }

    @RequestMapping(path = "/remove-friend{id}", method = RequestMethod.POST)
    public void removeFriend(@PathVariable("id") int id, Boolean friend, HttpSession session) {

    }

    ///////////////
    //PHOTO ROUTES
    //////////////

    @RequestMapping(path = "/upload-photo", method = RequestMethod.POST)
    public String uploadPhoto(String imgLink, HttpSession session) {

        return "redirect:/account.html";
    }

    @RequestMapping(path = "/edit-photo{id}", method = RequestMethod.PUT)
    public String editPhoto(@PathVariable("id") int id, String imgLink, HttpSession session) {

        return "redirect:/account.html";
    }

    //Will need a default picture for users who don't have or delete images.
    @RequestMapping(path = "/remove-photo{id}", method = RequestMethod.DELETE)
    public String removePhoto(@PathVariable("id") int id) {

        return "redirect:/account.html";

    }

    /////////////////
    //MESSAGE ROUTES
    ////////////////

    @RequestMapping(path = "/save-message", method = RequestMethod.POST)
    public void saveMessage(HttpSession session, String message) throws Exception {
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            throw new Exception("You must be logged in to send messages");
        }
        User user = users.findByUserName(userName);
        Message newMessage = new Message(message, user);

        messages.save(newMessage);
    }
}
