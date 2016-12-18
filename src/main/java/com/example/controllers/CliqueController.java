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


import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public String createUser(@RequestBody User newUser, HttpSession session) throws Exception {

        if (newUser.getUserName().equals(users.findByUserName(newUser.getUserName()))) {
            throw new Exception("Username in use");
        } else if (newUser.getUserName() == null) {
            throw new Exception("Username cannot be blank");
        } else if (newUser.getPassword() == null) {
            throw new Exception("Password cannot be blank");
        } else {
            newUser = new User(newUser.getUserName(), newUser.getEmail(),
                    PasswordStorage.createHash(newUser.getPassword()), newUser.getFirstName(), newUser.getLastName(), "default.jpg");

            users.save(newUser);
            session.setAttribute("userName", newUser.getUserName());
            return newUser.getUserName();
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody User login, HttpSession session) throws Exception {

        User user = users.findByUserName(login.getUserName());

        if (login.getUserName() == null) {
            throw new Exception("This error");
        } else if (!PasswordStorage.verifyPassword(login.getPassword(), user.getPassword())) {
            throw new Exception("Invalid password");
        } else {
            session.setAttribute("userName", login.getUserName());
            return login.getUserName();
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();

    }

    @RequestMapping(path = "/getname", method = RequestMethod.GET)
    public String hitThisShit(HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findByUserName(userName);
        return user.getUserName();
    }

    @RequestMapping(path = "/account-load", method = RequestMethod.GET)
    public User passUser(HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findByUserName(userName);
        return new User(user.getUserName(), user.getEmail(), user.getFirstName(), user.getLastName());

    }

    @RequestMapping(path = "/remove", method = RequestMethod.DELETE)
    public void remove(@RequestBody User user, HttpSession session) throws Exception {

        User curUser = users.findByUserName((String) session.getAttribute("userName"));
        int del = curUser.getId();
        if (PasswordStorage.verifyPassword(user.getPassword(), curUser.getPassword())) {
            session.invalidate();
            users.delete(del);
        } else {
            throw new Exception("Password do not match!");
        }
    }

    @RequestMapping(path = "/addphoto", method = RequestMethod.PUT)
    public void addPhoto(@RequestBody User user, HttpSession session) {

        String curUser = (String) session.getAttribute("userName");
        User update = users.findByUserName(curUser);
        update.setImgLoc("image/" + user.getImgLoc());
        users.save(update);
    }


    @RequestMapping(path = "/page-load", method = RequestMethod.GET)
    public User getUser(HttpSession session) {

        String userName = (String) session.getAttribute("userName");
        User user = users.findByUserName(userName);
        return user;
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public User editUser(HttpSession session, @RequestBody User thisUser) {




        return null;

    }
}