package com.dumanskiy.dao;

import com.dumanskiy.model.Question;
import com.dumanskiy.model.User;

import java.util.List;

public interface DAO {
    void connect();
    void disconnect();
    List<Question> selectQuestions();
    User checkExisting(String user);
}
