import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFunctions {

    public String minEstimateValue() {

        String query = "SELECT ROUND(MIN(list_price), 2) AS min_price FROM ("
                + "SELECT list_price FROM home_sold "
                + "UNION ALL SELECT list_price FROM home_for_sale "
                + "UNION ALL SELECT list_price FROM home_expired) AS combined_list";

        String min = null;

        try (Connection connection = DatabaseConnector.connect()) {

            ResultSet resultSet = connection.createStatement().executeQuery(query);

            if (resultSet.next()) {
                min = resultSet.getString("min_price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return min;

    }

    public String maxEstimateValue() {
        String query = "SELECT ROUND(MAX(list_price), 2) AS max_price FROM ("
                + "SELECT list_price FROM home_sold "
                + "UNION ALL SELECT list_price FROM home_for_sale "
                + "UNION ALL SELECT list_price FROM home_expired) AS combined_list";

        String max = null;

        try (Connection connection = DatabaseConnector.connect()) {

            ResultSet resultSet = connection.createStatement().executeQuery(query);

            if (resultSet.next()) {
                max = resultSet.getString("max_price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return max;
    }

    public String avgEstimateValue() {
        String query1 = "SELECT list_price FROM home_sold";
        String query2 = "SELECT list_price FROM home_for_sale";
        String query3 = "SELECT list_price FROM home_expired";

        double sum = 0;
        int count = 0;
        double avg;
        String avgRound = "";

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


                avgRound = String.format("%.2f", avg);

            } else {
                System.out.println("No list prices found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return avgRound;
    }

    static class Property {
        String address;
        int erf_size;
        int living_room;
        int bedroom;
        int bathroom;
        int garage;
        boolean pool;
        boolean flat;
        boolean domestic_quar;
        String other_detail;
        int days_on_market;
        double list_price;

        public Property(String address, int erf_size, int living_room, int bedroom, int bathroom, int garage, boolean pool, boolean flat, boolean domestic_quar, String other_detail, int days_on_market, double list_price) {
            this.address = address != null ? address : "Unknown Address";
            this.erf_size = Math.max(erf_size, 0);
            this.living_room = Math.max(living_room, 0);
            this.bedroom = Math.max(bedroom, 0);
            this.bathroom = Math.max(bathroom, 0);
            this.garage = Math.max(garage, 0);
            this.pool = pool;
            this.flat = flat;
            this.domestic_quar = domestic_quar;
            this.other_detail = other_detail != null ? other_detail : "No Details Available";
            this.days_on_market = Math.max(days_on_market, 0);
            this.list_price = list_price >= 0 ? list_price : 0.0;

        }
    }

    static class PropertySold {
        String address;
        int erf_size;
        int living_room;
        int bedroom;
        int bathroom;
        int garage;
        boolean pool;
        boolean flat;
        boolean domestic_quar;
        String other_detail;
        int days_on_market;
        double list_price;
        double sold_price;

        public PropertySold(String address, int erf_size, int living_room, int bedroom, int bathroom, int garage, boolean pool, boolean flat, boolean domestic_quar, String other_detail, int days_on_market, double list_price, double sold_price) {
            this.address = address != null ? address : "Unknown Address";
            this.erf_size = Math.max(erf_size, 0);
            this.living_room = Math.max(living_room, 0);
            this.bedroom = Math.max(bedroom, 0);
            this.bathroom = Math.max(bathroom, 0);
            this.garage = Math.max(garage, 0);
            this.pool = pool;
            this.flat = flat;
            this.domestic_quar = domestic_quar;
            this.other_detail = other_detail != null ? other_detail : "No Details Available";
            this.days_on_market = Math.max(days_on_market, 0);
            this.list_price = list_price >= 0 ? list_price : 0.0;
            this.sold_price = sold_price >= 0 ? sold_price : 0.0;

        }
    }

    public static List<Property> fetchPropertiesForSale() throws SQLException {
        return fetchProperties("SELECT * FROM home_for_sale LIMIT 4");
    }

    public static List<PropertySold> fetchPropertiesSold() throws SQLException {
        return fetchPropertiesWithSoldPrice("SELECT * FROM home_sold LIMIT 4");
    }

    public static List<Property> fetchPropertiesExpired() throws SQLException {
        return fetchProperties("SELECT * FROM home_expired LIMIT 4");
    }

    public static List<Property> fetchPropertiesYourProp() throws SQLException {
        return fetchProperties("SELECT * FROM your_property");
    }

    private static List<Property> fetchProperties(String query) throws SQLException {
        List<Property> propertyList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Property property = new Property(
                        resultSet.getString("address"),
                        resultSet.getInt("erf_size"),
                        resultSet.getInt("living_room"),
                        resultSet.getInt("bedroom"),
                        resultSet.getInt("bathroom"),
                        resultSet.getInt("garage"),
                        resultSet.getBoolean("pool"),
                        resultSet.getBoolean("flat"),
                        resultSet.getBoolean("domestic_quar"),
                        resultSet.getString("other_detail"),
                        resultSet.getInt("days_on_market"),
                        resultSet.getDouble("list_price")
                );
                propertyList.add(property);
            }
        }
        return propertyList;
    }

    private static List<PropertySold> fetchPropertiesWithSoldPrice(String query) throws SQLException {
        List<PropertySold> propertyListSold = new ArrayList<>();

        try (Connection connection = DatabaseConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                PropertySold propertySold = new PropertySold(
                        resultSet.getString("address"),
                        resultSet.getInt("erf_size"),
                        resultSet.getInt("living_room"),
                        resultSet.getInt("bedroom"),
                        resultSet.getInt("bathroom"),
                        resultSet.getInt("garage"),
                        resultSet.getBoolean("pool"),
                        resultSet.getBoolean("flat"),
                        resultSet.getBoolean("domestic_quar"),
                        resultSet.getString("other_detail"),
                        resultSet.getInt("days_on_market"),
                        resultSet.getDouble("list_price"),
                        resultSet.getDouble("sold_price")
                );
                propertyListSold.add(propertySold);
            }
        }
        return propertyListSold;
    }
}
