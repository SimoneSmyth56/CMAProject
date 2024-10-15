import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CMAFunctionality {

    static class numberList {

        String[] numberChoice = {"0", "1","2","3","4","5","6","7","8","9","10"};
    }

    static class YNList {

        String[] YNChoice = {"Yes","No"};
    }

    static class AddingProperties {

        public String addHomesSold(String address, int erf_size, int living_room, int bedroom, int bathroom, int garage, boolean pool, boolean flat, boolean domestic_quar, String other_detail , int days_on_market, double list_price, double sold_price) {
            try (Connection connection = DatabaseConnector.connect()) {
                String sql = "INSERT INTO home_sold (address, erf_size, living_room, bedroom, bathroom, garage, pool, flat, domestic_quar, other_detail , days_on_market, list_price, sold_price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, address);
                ps.setInt(2, erf_size);
                ps.setInt(3, living_room);
                ps.setInt(4, bedroom);
                ps.setInt(5, bathroom);
                ps.setInt(6, garage);
                ps.setBoolean(7, pool);
                ps.setBoolean(8, flat);
                ps.setBoolean(9, domestic_quar);
                ps.setString(10, other_detail);
                ps.setInt(11, days_on_market);
                ps.setDouble(12, list_price);
                ps.setDouble(13, sold_price);

                ps.executeUpdate();

            } catch (SQLException e) {
                return "An error occurred during adding of the property" + e.getMessage();
            }
            return "The property has been added. Add another property or click \"Next\" to go the the next screen";
        }

        public String addHomesForSale(String address, int erf_size, int living_room, int bedroom, int bathroom, int garage, boolean pool, boolean flat, boolean domestic_quar, String other_detail , int days_on_market, double list_price) {
            try (Connection connection = DatabaseConnector.connect()) {
                String sql = "INSERT INTO home_for_sale (address, erf_size, living_room, bedroom, bathroom, garage, pool, flat, domestic_quar, other_detail , days_on_market, list_price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, address);
                ps.setInt(2, erf_size);
                ps.setInt(3, living_room);
                ps.setInt(4, bedroom);
                ps.setInt(5, bathroom);
                ps.setInt(6, garage);
                ps.setBoolean(7, pool);
                ps.setBoolean(8, flat);
                ps.setBoolean(9, domestic_quar);
                ps.setString(10, other_detail);
                ps.setInt(11, days_on_market);
                ps.setDouble(12, list_price);

                ps.executeUpdate();

            } catch (SQLException e) {
                return "An error occurred during adding of the property" + e.getMessage();
            }

            return "The property has been added. Add another property or click \"Next\" to go the the next screen";

        }

        public String addHomesExpired(String address, int erf_size, int living_room, int bedroom, int bathroom, int garage, boolean pool, boolean flat, boolean domestic_quar, String other_detail , int days_on_market, double list_price, double sold_price) {
            try (Connection connection = DatabaseConnector.connect()) {
                String sql = "INSERT INTO home_expired (address, erf_size, living_room, bedroom, bathroom, garage, pool, flat, domestic_quar, other_detail , days_on_market, list_price, sold_price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, address);
                ps.setInt(2, erf_size);
                ps.setInt(3, living_room);
                ps.setInt(4, bedroom);
                ps.setInt(5, bathroom);
                ps.setInt(6, garage);
                ps.setBoolean(7, pool);
                ps.setBoolean(8, flat);
                ps.setBoolean(9, domestic_quar);
                ps.setString(10, other_detail);
                ps.setInt(11, days_on_market);
                ps.setDouble(12, list_price);
                ps.setDouble(13, sold_price);

                ps.executeUpdate();

            } catch (SQLException e) {
                return "An error occurred during adding of the property" + e.getMessage();
            }
            return "The property has been added. Add another property or click \"Next\" to go the the next screen";
        }



    }

}
