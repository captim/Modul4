package com.dumanskiy.dao;

import com.dumanskiy.model.Question;
import com.dumanskiy.model.User;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleDAO implements DAO{
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private static OracleDAO oracleDAO;
    private OracleDAO() {

    }
    public static OracleDAO getInstance() {
        if (oracleDAO == null) {
            oracleDAO = new OracleDAO();
        }
        return oracleDAO;
    }
    @Override
    public void connect() {
        Driver driver = null;
        try {
            driver = (Driver) Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        try {
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "student");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection!!!");
    }


    @Override
    public void disconnect() {
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> selectQuestions() {
        connect();
        List<Question> list = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM QUESTIONS");
            list = getQuestions(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return list;
    }

    @Override
    public User checkExisting(String name) {
        connect();
        try {
            statement = connection.prepareStatement("SELECT * FROM USERS WHERE NAME = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setMaxMark(resultSet.getInt(3));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User();
    }

    private List<Question> getQuestions(ResultSet resultSet) throws SQLException {
        List<Question> list = new ArrayList<>();
        do {
            Question question = new Question();
            question.setId(resultSet.getInt(1));
            question.setQuestion(resultSet.getString(2));
            question.setAnswer(resultSet.getString(3));
            question.setPoints(resultSet.getInt(4));
            list.add(question);
        } while (resultSet.next());
        return list;
    }
}
