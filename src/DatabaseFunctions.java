import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseFunctions {

    public int minEstimateValue () {
        int min = Integer.MIN_VALUE;




        return min;
    }

    public int maxEstimateValue () {
        int max = Integer.MAX_VALUE;


        return max;
    }

    public double avgEstimateValue () {
        String query1 = "SELECT list_price FROM home_sold";
        String query2 = "SELECT list_price FROM home_for_sale";
        String query3 = "SELECT list_price FROM home_expired";

        double sum = 0;
        int count = 0;
        double avg = 0;

        try (Connection connection = DatabaseConnector.connect()) {


            ResultSet resultSet1 = connection.createStatement().executeQuery(query1);
            while (resultSet1.next()) {
                sum += resultSet1.getDouble("list_price");
                count++;
            }


            ResultSet resultSet2 = connection.createStatement().executeQuery(query2);
            while (resultSet2.next()) {
                sum += resultSet2.getDouble("list_price");
                count++;
            }

            ResultSet resultSet3 = connection.createStatement().executeQuery(query3);
            while (resultSet3.next()) {
                sum += resultSet3.getDouble("list_price");
                count++;
            }

            if (count > 0) {
                avg = sum / count;
                System.out.println("Average List Price: " + avg);
            } else {
                System.out.println("No list prices found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return avg;

    }

}
