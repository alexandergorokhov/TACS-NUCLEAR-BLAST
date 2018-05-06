package com.tacs.service;

import com.tacs.controller.login.LoginInterface;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginInterface {

    @Override
    public String login() {
        return "login";
    }

    @Override
    public String logout() {
        return "logout";
    }
}
