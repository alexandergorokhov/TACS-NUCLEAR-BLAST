package com.tacs.controller.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface LoginInterface {
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login() ;

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout() ;
}
