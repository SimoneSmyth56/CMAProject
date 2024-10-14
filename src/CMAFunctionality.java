import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

        public String addHomesSold(String address,int livingRooms, int bedrooms, int bathrooms, int garages, String pool, String flat, String domesticQuarters, String otherDetails , int daysOnMarket, double listPrice, double soldPrice) {
            try (Connection connection = DatabaseConnector.connect()) {
                String sql = "INSERT INTO HomesSold (address, livingRooms, bedrooms, bathrooms, garages, pool, flat, domesticQuarters, otherDetails , daysOnMarket, listPrice, soldPrice) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setString(1, address);
                ps.setInt(2, livingRooms);
                ps.setInt(3, bedrooms);
                ps.setInt(4, bathrooms);
                ps.setInt(5, garages);
                ps.setString(6, pool);
                ps.setString(7, flat);
                ps.setString(8, domesticQuarters);
                ps.setString(9, otherDetails);
                ps.setInt(10, daysOnMarket);
                ps.setDouble(11, listPrice);
                ps.setDouble(12, soldPrice);

                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                return "An error occurred during adding of the property";
            }

            return "Your property has been added!";

        }



    }

//    static class FileHandling {
//
//    public String ImageUploader() {
//        // width of the image
//        int width = 963;
//
//        // height of the image
//        int height = 640;
//
//        // For storing image in RAM
//        BufferedImage image = null;
//
//        FileDialog loadFileDialog = new FileDialog((Frame) null, "Open File", FileDialog.LOAD);
//        loadFileDialog.setVisible(true);
//
//        String selectedReadFile = loadFileDialog.getFile();
//
//        // READ IMAGE
//        try {
//            File input_file = new File(loadFileDialog.getDirectory() + selectedReadFile);
//
//            // image file path create an object of BufferedImage type and pass as parameter the
//            // width,  height and image int type. TYPE_INT_ARGB means that we are
//            // representing the Alpha , Red, Green and Blue component of the image pixel using 8 bit
//            // integer value.
//
//            image = new BufferedImage(
//                    width, height, BufferedImage.TYPE_INT_ARGB);
//
//            // Reading input file
//            image = ImageIO.read(input_file);
//
//            return "Image uploaded.";
//        } catch (IOException e) {
//            return "Image not Uploaded. Error: " + e;
//        }
//    }
//    }
}
