package com.tacs.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
public class User {
    private Integer id;
    private String name;
    private String surname;
    private String nick;
    private Byte age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nick='" + nick + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, String surname, Byte age, String nick) {
        this.id = new Random().nextInt();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.nick = nick;
    }

    public User(){

    }

}
