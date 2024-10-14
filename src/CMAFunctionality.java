import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CMAFunctionality {
    static class numberList {

        String[] numberChoice = {"0", "1","2","3","4","5","6","7","8","9","10"};
    }

    static class YNList {

        String[] YNChoice = {"Yes","No"};
    }

    static class AddingProperties {

//        ArrayList<Property> propertiesForSale = new ArrayList<>();

    }

    static class FileHandling {

    public String ImageUploader() {
        // width of the image
        int width = 963;

        // height of the image
        int height = 640;

        // For storing image in RAM
        BufferedImage image = null;

        FileDialog loadFileDialog = new FileDialog((Frame) null, "Open File", FileDialog.LOAD);
        loadFileDialog.setVisible(true);

        String selectedReadFile = loadFileDialog.getFile();

        // READ IMAGE
        try {
            File input_file = new File(loadFileDialog.getDirectory() + selectedReadFile);

            // image file path create an object of BufferedImage type and pass as parameter the
            // width,  height and image int type. TYPE_INT_ARGB means that we are
            // representing the Alpha , Red, Green and Blue component of the image pixel using 8 bit
            // integer value.

            image = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_ARGB);

            // Reading input file
            image = ImageIO.read(input_file);

            return "Image uploaded.";
        } catch (IOException e) {
            return "Image not Uploaded. Error: " + e;
        }
    }
    }
}
