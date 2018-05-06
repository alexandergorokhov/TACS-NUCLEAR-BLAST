package com.tacs.service;

import com.tacs.controller.user.UserInterface;
import com.tacs.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@Service
public class UserService implements UserInterface {

    //UNTIL DB IS SET UP (NIGGA TEMP STUFF)
    private static HashMap<Integer, User> users = new HashMap<>();

    public static HashMap getUsers() {
        return users;
    }

    @Override
    public User register(User user) {
        User userLocal = new User(user.getName(),user.getSurname(),user.getAge(),user.getNick());
        users.put(userLocal.getId(),userLocal);
        return user;
    }

    @Override
    public String getUser(String id) {
        return users.get(Integer.valueOf(id)).toString();
    }

    @Override
    public String buyCrypto() {
        return "Buying crypto and amount";
    }

    @Override
    public String sellCrypto() {
        return "Selling crypto and amount";
    }

    @Override
    public String seePortfolio() {
        return "Viewing  quantity and date, eg Portfolio";
    }

    @Override
    public String seeTransactions() {
        return "Viewing type and quantity and date";
    }

    @Override
    public String seePortfolioTelegram() {
        return "Viewing type and quantity and date, eg Portfolio over Telegram";
    }

    @Override
    public String buyCryptoTelegram() {
        return "Buying type and quantity Telegram";
    }

    @Override
    public String sellCryptoTelegram() {
        return "Selling type and quantity Telegram";
    }

    @Override
    public String seeCotizationTelegram() {
        return "Seing cotization via Telegram";
    }

    @Override
    public String getTransaction() {
        return "Transactions for a paticular user";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public HashMap list() {
        users.forEach((k, v) -> System.out.println(v.toString()));
        return users;
    }
}
