package com.example.controllers;

import com.example.entities.Message;
import com.example.entities.User;
import com.example.services.CliqueRepository;
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
    @Autowired
    CliqueRepository clique;

    //////////////////////
    //USER ACCOUNT ROUTES
    /////////////////////

    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public void createUser(@RequestBody User newUser, HttpSession session) throws Exception {


        if (newUser.getUserName() == null) {
            throw new Exception("Username cannot be blank");
        }
        if (newUser.getPassword() == null) {
            throw new Exception("Password cannot be blank");
        }
        User user = users.findByUserName(newUser.getUserName());
        if (user == null) {
            user = new User(newUser.getUserName(), newUser.getEmail(),
                    PasswordStorage.createHash(newUser.getPassword()),
                    newUser.getFirstName(), newUser.getLastName(), "default");

            users.save(user);
            session.setAttribute("userName", newUser.getUserName());


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
    public void loginUser(@RequestBody User loginAttempt, HttpSession session) throws Exception {

        User user = users.findByUserName(loginAttempt.getUserName());

        if (loginAttempt.getUserName() == null) {
            throw new Exception("Username cannot be blank");
        }
        if (loginAttempt.getPassword() == null) {
            throw new Exception("Password cannot be blank");
        }
        if (loginAttempt.getUserName().equals(user.getUserName()) &&
                PasswordStorage.verifyPassword(loginAttempt.getPassword(), user.getPassword())) {
            session.setAttribute("userName", loginAttempt.getUserName());

        }else{
            throw new Exception("Well fuck.");
        }


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

    ////////////////////////
    //DIRECT MESSAGE ROUTES
    ///////////////////////

    @RequestMapping(path = "/save-message", method = RequestMethod.POST)

    //This code is going to need to be changed drastically.
    //Currently does not save who message was sent to.

    public void saveMessage(HttpSession session, @RequestBody Message message) throws Exception {
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            throw new Exception("You must be logged in to send messages");
        }
        //User user = users.findByUserName(userName);
        //Message newMessage = new Message(message, user);

        messages.save(message);
    }

    /////////////////
    //CLIQUE ROUTES
    ////////////////

    @RequestMapping(path = "/clique-message", method = RequestMethod.POST)
    public void cliqueMessage(HttpSession session, String message) throws Exception {
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            throw new Exception("You must be logged in to chat");
        }

    }

    @RequestMapping(path = "/email", method = RequestMethod.GET, produces = "application/json")
    public String hitThisShit(HttpSession session) {

        String userName = (String) session.getAttribute("userName");
        return userName;
    }

}
