package com.tacs.crypto;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.tacs.App;
import com.tacs.controller.user.UserController;
import com.tacs.pojo.User;
import com.tacs.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void testCreateUser() throws Exception {

        User user = new User("testName", "testSurname", Byte.valueOf("0"), "testNick");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/")
            .content(new Gson().toJson(user)).contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(builder).andReturn().getResponse();


        User userResponsePojo = new Gson().fromJson(response.getContentAsString(), User.class);

        Assert.assertEquals(user.getName(), userResponsePojo.getName());
        Assert.assertEquals(user.getSurname(), userResponsePojo.getSurname());
        Assert.assertEquals(user.getAge(), userResponsePojo.getAge());
        Assert.assertEquals(user.getNick(), userResponsePojo.getNick());

    }

    @Test
    public void testGetUser() throws Exception {
        User user = new User("testName", "testSurname", new Byte("0"), "testNick");
        Integer id = user.getId();
        UserService.getUsers().put(user.getId(), user);
        when().get("/users/" + user.getId().toString())
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
        when().get("/users/seePortfolio/1/" + LocalDate.parse("2016-08-16").toString()).then()
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
