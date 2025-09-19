package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDataProvider {
    @DataProvider(name = "locationData")
    public static Object[][] getLocations() {
        try (Connection connection = MSSQLConnection.connect()) {
            String SQL = "SELECT id, area FROM location_cases";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            Object[][] data = new Object[rowCount][resultSet.getMetaData().getColumnCount()];
            int iter = 0;
            while (resultSet.next()) {
                data[iter++] = new Object[]{
                        resultSet.getString("id"),
                        resultSet.getString("area")
                };
            }
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
