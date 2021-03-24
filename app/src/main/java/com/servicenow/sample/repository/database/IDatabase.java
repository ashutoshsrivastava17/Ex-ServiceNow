package com.servicenow.sample.repository.database;


import com.servicenow.sample.repository.database.dao.JokeDao;

public interface IDatabase {

    JokeDao getJokeDao();

}
