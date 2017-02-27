package edu.wwu.center.studenttechnology.stc.alexa.sketchfab.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private final String username = "";
    private final String password = "";
    private final String databaseURL = "";
    private final String tableName = "";
    private final String searchCol = "";

    private Connection connection;

    public DatabaseHandler() {
        resetValues();
    }

    private void resetValues() {
        openConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("Truncate table " + tableName);
            insert(statement, "WAIT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    private void insert(Statement statement, String value) throws SQLException {
        statement.execute("INSERT INTO " + tableName + " (" + searchCol
                + ") VALUES ('" + value + "')");
    }

    private String get(Statement statement, int id) {
        try {
            ResultSet rs = statement
                    .executeQuery("SELECT " + searchCol + " FROM " + tableName);
            return rs.getString(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void openConnection() {
        try {
            connection = DriverManager.getConnection(databaseURL, username,
                    password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void insertValue(String value) {
        openConnection();
        try {
            insert(connection.createStatement(), value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public void setAction(String value) {
        // TODO: Enum list
        openConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE " + tableName + " SET " + searchCol
                    + " WHERE id=1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getResults(int startingBound, int endingBound) {
        List<String> resultList = new ArrayList<String>();

        openConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            for (int i = startingBound; i < endingBound; i++) {
                resultList.add(get(statement, i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return resultList;
    }
}
