package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping
  public List<com.example.demo.domain.entity.Users> getUser() {

    List<com.example.demo.domain.entity.Users> users = new ArrayList<>();

    com.example.demo.domain.entity.Users user = new com.example.demo.domain.entity.Users();
    user.setId(1);
    user.setName("主田");
    user.setAge(24);
    user.setPersonalColor("blue");

    users.add(user);


    com.example.demo.domain.entity.Users user1 = new com.example.demo.domain.entity.Users();
    user1.setId(2);
    user1.setName("先岡");
    user1.setAge(28);
    user1.setPersonalColor("pink");
    users.add(user1);

    com.example.demo.domain.entity.Users user2 = new com.example.demo.domain.entity.Users();
    user2.setId(3);
    user2.setName("後藤");
    user2.setAge(23);
    user2.setPersonalColor("green");
    users.add(user2);
    return users;
  }
}
