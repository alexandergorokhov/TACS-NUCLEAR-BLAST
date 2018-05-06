package com.tacs.controller.login;

import com.tacs.service.LoginService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@Getter
@Setter
public class LoginController implements LoginInterface {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login() {
        return loginService.login();
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout() {
        return loginService.logout();
    }
}
