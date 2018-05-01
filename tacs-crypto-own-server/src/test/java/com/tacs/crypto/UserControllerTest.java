package com.tacs.crypto;

import com.jayway.restassured.RestAssured;
import com.tacs.App;
import com.tacs.controller.user.UserController;
import com.tacs.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:com.tacs.crypto;DB_CLOSE_ON_EXIT=FALSE"})
public class UserControllerTest {
    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void testLogin() throws Exception {
        when().post("/users/login").then()
                .body(is("login"));
    }

    @Test
    public void testLogout() throws Exception {
        when().post("/users/logout").then()
                .body(is("logout"));
    }

    @Test
    public void testCreateUser() throws Exception {
        when().post("/users/create/testName/testSurname/0/testNick").then()
                .body(is("User{" +
                        "name='" + "testName" + '\'' +
                        ", surname='" + "testSurname" + '\'' +
                        ", nick='" + "testNick" + '\'' +
                        ", age=" + "0" +
                        '}'));
    }

    @Test
    public void testGetUser() throws Exception {
        User user = new User("testName", "testSurname", new Byte("0"), "testNick");
        Integer id = user.getId();
        UserController.getUsers().put(user.getId(),user);
        when().get("/users/"+user.getId().toString())
                .then()
                .body(is("User{" +
                        "name='" + "testName" + '\'' +
                        ", surname='" + "testSurname" + '\'' +
                        ", nick='" + "testNick" + '\'' +
                        ", age=" + "0" +
                        '}'));
    }

    @Test
    public void testBuyCrypto() throws Exception {
        when().post("/users/1/transactions/buy/btc/1").then()
                .body(is("Buying crypto and amount"));
    }
    @Test
    public void testSellCrypto() throws Exception {
        when().put("/users/1/transactions/sell/btc/1").then()
                .body(is("Selling crypto and amount"));
    }
    @Test
    public void testSeePortfolio() throws Exception {
        when().get("/users/seePortfolio/1/"+LocalDate.parse("2016-08-16").toString()).then()
                .body(is("Viewing  quantity and date, eg Portfolio"));
    }

    @Test
    public void testSeeTransactions() throws Exception {
        when().get("/users/seeTransactions/1/btc").then()
                .body(is("Viewing type and quantity and date"));
    }

    @Test
    public void testSeePortfolioTelegram() throws Exception {
        when().get("users/telegram/portfolio/1/btc").then()
                .body(is("Viewing type and quantity and date, eg Portfolio over Telegram"));
    }

    @Test
    public void testBuyCryptoTelegram() throws Exception {
        when().post("users/telegram/buy/1/btc/1").then()
                .body(is("Buying type and quantity Telegram"));
    }
    @Test
    public void testSellCryptoTelegram() throws Exception {
        when().put("users/telegram/sell/1/btc/1").then()
                .body(is("Selling type and quantity Telegram"));
    }
    @Test
    public void testSeeCotizationTelegram() throws Exception {
        when().get("users/telegram/cotization/1/btc").then()
                .body(is("Seing cotization via Telegram"));
    }
    @Test
    public void testGetTransaction() throws Exception {
        when().get("users/getTransactions/1/transactions/1").then()
                .body(is("Transactions for a paticular user"));
    }
}
