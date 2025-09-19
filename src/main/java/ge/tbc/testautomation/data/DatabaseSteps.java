package ge.tbc.testautomation.data.jdbc;

import ge.tbc.testautomation.data.MSSQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSteps {

    public ResultSet getAllRegistrationData() {
        try {
            Connection connection = MSSQLConnection.connect();

            Statement statement = connection.createStatement();

            return statement.executeQuery("SELECT id, area FROM RegistrationData");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}