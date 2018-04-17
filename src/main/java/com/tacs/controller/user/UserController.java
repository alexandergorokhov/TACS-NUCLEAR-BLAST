package com.tacs.controller.user;

import com.tacs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private HashMap<Integer, User> users = new HashMap<>();

    @RequestMapping(value = "/user/create/{name}/{surname}/{age}/{nick}", method = RequestMethod.GET)
    String register(@PathVariable String name, @PathVariable String surname, @PathVariable  Byte age, @PathVariable String nick) {
        User user = new User(name, surname, age,nick);
        users.put(user.getId(),user);
        return user.toString();
    }

    @RequestMapping(value = "/user/list/", method = RequestMethod.GET)
    HashMap list() {
        users.forEach((k,v)->System.out.println(v.toString()));
        return users;

    }
}
