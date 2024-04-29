package com.github.tushargoel1.demo.controller;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.tushargoel1.demo.dao.UserDao;

@RestController
@RequestMapping (value = "/v1/users")
public class UserController {

    @Autowired
    private Jdbi jdbiConnection;

    @GetMapping (path = "/sleep")
    public ResponseEntity<Integer> executeSleep() {
        int sleep = this.jdbiConnection.withExtension(UserDao.class, UserDao::executeSleep);
        return new ResponseEntity<>(sleep, org.springframework.http.HttpStatus.OK);
    }
}
