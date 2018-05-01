package com.tacs.controller.user;

import com.tacs.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/users")
@Getter
@Setter
public class UserController implements UserInterface {
    //UNTIL DB IS SET UP (NIGGA TEMP STUFF)
    private static HashMap<Integer, User> users = new HashMap<>();
    public static HashMap getUsers(){
        return users;
    }

    @RequestMapping(value = "create/{name}/{surname}/{age}/{nick}", method = RequestMethod.POST)
    public String register(@PathVariable String name, @PathVariable String surname, @PathVariable Byte age, @PathVariable String nick) {
        User user = new User(name, surname, age, nick);
        users.put(user.getId(), user);
        return user.toString();
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    HashMap list() {
        users.forEach((k, v) -> System.out.println(v.toString()));
        return users;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable String id) {

        return users.get(Integer.valueOf(id)).toString();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout() {
        return "logout";
    }

    @RequestMapping(value = "{id}/transactions/buy/{crypto}/{amount}", method = RequestMethod.POST)
    public String buyCrypto() {

        return "Buying crypto and amount";
    }

    @RequestMapping(value = "{id}/transactions/sell/{crypto}/{amount}", method = RequestMethod.PUT)
    public String sellCrypto() {

        return "Selling crypto and amount";
    }

    @RequestMapping(value = "seePortfolio/{id}/{date}", method = RequestMethod.GET)
    public String seePortfolio() {

        return "Viewing  quantity and date, eg Portfolio";
    }

    @RequestMapping(value = "seeTransactions/{id}/{crypto}", method = RequestMethod.GET)
    public String seeTransactions() {

        return "Viewing type and quantity and date";
    }


    //TODO research Telegram. This endpoints can be modified
    @RequestMapping(value = "telegram/portfolio/{id}/{crypto}", method = RequestMethod.GET)
    public String seePortfolioTelegram() {

        return "Viewing type and quantity and date, eg Portfolio over Telegram";
    }

    @RequestMapping(value = "telegram/buy/{id}/{type}/{quantity}", method = RequestMethod.POST)
    public String buyCryptoTelegram() {

        return "Buying type and quantity Telegram";
    }

    @RequestMapping(value = "telegram/sell/{id}/{type}/{quantity}", method = RequestMethod.PUT)
    public String sellCryptoTelegram() {

        return "Selling type and quantity Telegram";
    }

    @RequestMapping(value = "telegram/cotization/{id}/{type}", method = RequestMethod.GET)
    public String seeCotizationTelegram() {

        return "Seing cotization via Telegram";
    }

    @RequestMapping(value = "getTransactions/{id}/transactions/{idTransaction}", method = RequestMethod.GET)
    public String getTransaction() {

        return "Transactions for a paticular user";
    }


}
