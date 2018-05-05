package com.tacs.controller.user;

import com.tacs.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

public interface UserInterface {

    @RequestMapping(method = RequestMethod.POST)
    public User register(@RequestBody User user) ;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id) ;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login() ;

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout() ;

    @RequestMapping(value = "{id}/transactions/buy/{crypto}/{amount}", method = RequestMethod.POST)
    public String buyCrypto() ;

    @RequestMapping(value = "{id}/transactions/sell/{crypto}/{amount}", method = RequestMethod.PUT)
    public String sellCrypto() ;

    @RequestMapping(value = "seePortfolio/{id}/{date}", method = RequestMethod.GET)
    public String seePortfolio();

    @RequestMapping(value = "seeTransactions/{id}/{crypto}", method = RequestMethod.GET)
    public String seeTransactions();


    //TODO research Telegram. This endpoints can be modified
    @RequestMapping(value = "telegram/portfolio/{id}/{crypto}", method = RequestMethod.GET)
    public String seePortfolioTelegram();

    @RequestMapping(value = "telegram/buy/{id}/{type}/{quantity}", method = RequestMethod.POST)
    public String buyCryptoTelegram() ;

    @RequestMapping(value = "telegram/sell/{id}/{type}/{quantity}", method = RequestMethod.PUT)
    public String sellCryptoTelegram() ;

    @RequestMapping(value = "telegram/cotization/{id}/{type}", method = RequestMethod.GET)
    public String seeCotizationTelegram() ;

    @RequestMapping(value = "getTransactions/{id}/transactions/{idTransaction}", method = RequestMethod.GET)
    public String getTransaction();

}
