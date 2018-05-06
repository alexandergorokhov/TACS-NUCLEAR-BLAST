package com.tacs.controller.user;

import com.tacs.pojo.User;
import com.tacs.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/users")
@Getter
@Setter
public class UserController implements UserInterface {

    @Autowired
    UserService userService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody  User register(@RequestBody User userJson) {
        return userService.register(userJson);
}

    @RequestMapping(value = "list", method = RequestMethod.GET)
    HashMap list() {

        return userService.list();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id) {

        return userService.getUser(id);
    }

    @RequestMapping(value = "{id}/transactions/buy/{crypto}/{amount}", method = RequestMethod.POST)
    public String buyCrypto() {

        return userService.buyCrypto();
    }

    @RequestMapping(value = "{id}/transactions/sell/{crypto}/{amount}", method = RequestMethod.PUT)
    public String sellCrypto() {

        return userService.sellCrypto();
    }

    @RequestMapping(value = "seePortfolio/{id}/{date}", method = RequestMethod.GET)
    public String seePortfolio() {

        return userService.seePortfolio();
    }

    @RequestMapping(value = "seeTransactions/{id}/{crypto}", method = RequestMethod.GET)
    public String seeTransactions() {

        return userService.seeTransactions();
    }


    //TODO research Telegram. This endpoints can be modified
    @RequestMapping(value = "telegram/portfolio/{id}/{crypto}", method = RequestMethod.GET)
    public String seePortfolioTelegram() {

        return userService.seePortfolioTelegram();
    }

    @RequestMapping(value = "telegram/buy/{id}/{type}/{quantity}", method = RequestMethod.POST)
    public String buyCryptoTelegram() {

        return userService.buyCryptoTelegram();
    }

    @RequestMapping(value = "telegram/sell/{id}/{type}/{quantity}", method = RequestMethod.PUT)
    public String sellCryptoTelegram() {

        return userService.sellCryptoTelegram();
    }

    @RequestMapping(value = "telegram/cotization/{id}/{type}", method = RequestMethod.GET)
    public String seeCotizationTelegram() {

        return userService.seeCotizationTelegram();
    }

    @RequestMapping(value = "getTransactions/{id}/transactions/{idTransaction}", method = RequestMethod.GET)
    public String getTransaction() {

        return userService.getTransaction();
    }


}
