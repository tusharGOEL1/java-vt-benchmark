package com.github.tushargoel1.demo.dao;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface UserDao {
    @SqlQuery (value = "select sleep(1)")
    int executeSleep();
}
