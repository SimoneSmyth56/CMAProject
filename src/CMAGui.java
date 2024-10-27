import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CMAGui {

    //Declaration of fields:
    private final JTextArea addressForSale;
    private final JTextArea addressSold;
    private final JTextArea otherDetailForSale;
    private final JTextArea otherDetailSold;
    private final JTextArea addressExpired;
    private final JTextArea otherDetailExpired;
    private final JTextArea addressYourProp;
    private final JTextArea otherDetailYourProp;
    private final JTextField erfForSale;
    private final JTextField listPriceForSale;
    private final JTextField erfSold;
    private final JTextField listPriceSold;
    private final JTextField soldPriceSold;
    private final JTextField erfExpired;
    private final JTextField listPriceExpired;
    private final JTextField erfYourProp;
    private final JTextField listPriceYourProp;
    private final JComboBox<String> livingRoomForSale;
    private final JComboBox<String> bedroomForSale;
    private final JComboBox<String> bathroomForSale;
    private final JComboBox<String> garageForSale;
    private final JComboBox<String> poolForSale;
    private final JComboBox<String> flatForSale;
    private final JComboBox<String> domesticQuartForSale;
    private final JComboBox<String> livingRoomSold;
    private final JComboBox<String> bedroomSold;
    private final JComboBox<String> bathroomSold;
    private final JComboBox<String> garageSold;
    private final JComboBox<String> poolSold;
    private final JComboBox<String> flatSold;
    private final JComboBox<String> domesticQuartSold;
    private final JComboBox<String> livingRoomExpired;
    private final JComboBox<String> bedroomExpired;
    private final JComboBox<String> bathroomExpired;
    private final JComboBox<String> garageExpired;
    private final JComboBox<String> poolExpired;
    private final JComboBox<String> flatExpired;
    private final JComboBox<String> domesticQuartExpired;
    private final JComboBox<String> livingRoomYourProp;
    private final JComboBox<String> bedroomYourProp;
    private final JComboBox<String> bathroomYourProp;
    private final JComboBox<String> garageYourProp;
    private final JComboBox<String> poolYourProp;
    private final JComboBox<String> flatYourProp;
    private final JComboBox<String> domesticQuartYourProp;
    private final JComboBox<Integer> daysOnMarketSold;
    private final JComboBox<Integer> daysOnMarketForSale;
    private final JComboBox<Integer> daysOnMarketExpired;
    private final JComboBox<Integer> daysOnMarketYourProp;
    JTextField recommendedPriceInput;

    CMAFunctionality.numberList numberList = new CMAFunctionality.numberList();
    CMAFunctionality.YNList ynList = new CMAFunctionality.YNList();
    DatabaseFunctions databaseFunctions = new DatabaseFunctions();

    int propertiesAdded = 0;

    Color karisColour1 = new Color(40, 128, 129);

    public CMAGui() {

        //-----------------------------------------------------------------
        //                       Main Frame & Panels
        //-----------------------------------------------------------------

        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500, 300));

        JPanel topLabelPanel = new JPanel();
        topLabelPanel.setPreferredSize(new Dimension(500, 30));
        topLabelPanel.setLayout(new BoxLayout(topLabelPanel, BoxLayout.PAGE_AXIS));
        topLabelPanel.setBackground(karisColour1);

        JPanel middleMenuPanel = new JPanel();
        middleMenuPanel.setPreferredSize(new Dimension(500, 220));

        JPanel innerMenuPanel = new JPanel();
        innerMenuPanel.setLayout(new BoxLayout(innerMenuPanel, BoxLayout.Y_AXIS));
        innerMenuPanel.setPreferredSize(new Dimension(500, 220));
        innerMenuPanel.setBackground(karisColour1);
        middleMenuPanel.add(innerMenuPanel);

        JPanel containerPanel = new JPanel();
        containerPanel.add(topLabelPanel, BorderLayout.NORTH);
        containerPanel.add(middleMenuPanel, BorderLayout.CENTER);
        mainPanel.add(containerPanel);

        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Comparative Market Analysis");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topLabelPanel.add(titleLabel);

        BufferedImage logoImage;
        try {
            logoImage = ImageIO.read(new File("images/logo.jpg"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage.getScaledInstance(220, 150, Image.SCALE_SMOOTH)));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        innerMenuPanel.add(logoLabel);

        //-----------------------------------------------------------------
        //                       "Similar Homes Sold" Frame
        //-----------------------------------------------------------------
        JFrame homesSoldFrame = new JFrame();
        homesSoldFrame.setTitle("Similar homes recently sold");
        homesSoldFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        homesSoldFrame.setBounds(300, 90, 700, 600);
        homesSoldFrame.setResizable(false);

        Container cpHomesSold = homesSoldFrame.getContentPane();
        cpHomesSold.setBackground(karisColour1);
        cpHomesSold.setLayout(null);

        JLabel addressLblSold = new JLabel("Address");
        LabelSettings(addressLblSold);
        addressLblSold.setLocation(50, 15);
        cpHomesSold.add(addressLblSold);

        addressSold = new JTextArea(3, 20);
        addressSold.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        addressSold.setSize(250, 50);
        addressSold.setLineWrap(true);
        addressSold.setWrapStyleWord(true);
        addressSold.setBorder(BorderFactory.createCompoundBorder(addressSold.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        addressSold.setLocation(253, 15);
        cpHomesSold.add(addressSold);
        AutoCompleteAddress(addressSold,cpHomesSold);


        JLabel erfLblSold = new JLabel("<html>Erf m<sup>2</sup></html>");
        LabelSettings(erfLblSold);
        erfLblSold.setLocation(50, 70);
        cpHomesSold.add(erfLblSold);

        erfSold = new JTextField();
        erfSold.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        erfSold.setSize(100, 20);
        erfSold.setLocation(250, 70);
        cpHomesSold.add(erfSold);
        NumericInputValidation(erfSold,cpHomesSold);

        JLabel livingRoomLblSold = new JLabel("Living Rooms");
        LabelSettings(livingRoomLblSold);
        livingRoomLblSold.setLocation(50, 100);
        cpHomesSold.add(livingRoomLblSold);

        livingRoomSold = new JComboBox<>(numberList.numberChoice);
        livingRoomSold.setFont(new Font("Arial", Font.PLAIN, 12));
        livingRoomSold.setSize(190, 20);
        livingRoomSold.setLocation(250, 100);
        livingRoomSold.setSelectedItem(null);
        cpHomesSold.add(livingRoomSold);

        JLabel bedroomLblSold = new JLabel("Bedrooms");
        LabelSettings(bedroomLblSold);
        bedroomLblSold.setLocation(50, 130);
        cpHomesSold.add(bedroomLblSold);

        bedroomSold = new JComboBox<>(numberList.numberChoice);
        bedroomSold.setFont(new Font("Arial", Font.PLAIN, 12));
        bedroomSold.setSize(190, 20);
        bedroomSold.setLocation(250, 130);
        bedroomSold.setSelectedItem(null);
        cpHomesSold.add(bedroomSold);

        JLabel bathroomLblSold = new JLabel("Bathrooms");
        LabelSettings(bathroomLblSold);
        bathroomLblSold.setLocation(50, 160);
        cpHomesSold.add(bathroomLblSold);

        bathroomSold = new JComboBox<>(numberList.numberChoice);
        bathroomSold.setFont(new Font("Arial", Font.PLAIN, 12));
        bathroomSold.setSize(190, 20);
        bathroomSold.setLocation(250, 160);
        bathroomSold.setSelectedItem(null);
        cpHomesSold.add(bathroomSold);

        JLabel garageLblSold = new JLabel("Garage");
        LabelSettings(garageLblSold);
        garageLblSold.setLocation(50, 190);
        cpHomesSold.add(garageLblSold);

        garageSold = new JComboBox<>(numberList.numberChoice);
        garageSold.setFont(new Font("Arial", Font.PLAIN, 12));
        garageSold.setSize(190, 20);
        garageSold.setLocation(250, 190);
        garageSold.setSelectedItem(null);
        cpHomesSold.add(garageSold);

        JLabel poolLblSold = new JLabel("Pool");
        LabelSettings(poolLblSold);
        poolLblSold.setLocation(50, 220);
        cpHomesSold.add(poolLblSold);

        poolSold = new JComboBox<>(ynList.YNChoice);
        poolSold.setFont(new Font("Arial", Font.PLAIN, 12));
        poolSold.setSize(190, 20);
        poolSold.setLocation(250, 220);
        poolSold.setSelectedItem(null);
        cpHomesSold.add(poolSold);

        JLabel flatLblSold = new JLabel("Flat");
        LabelSettings(flatLblSold);
        flatLblSold.setLocation(50, 250);
        cpHomesSold.add(flatLblSold);

        flatSold = new JComboBox<>(ynList.YNChoice);
        flatSold.setFont(new Font("Arial", Font.PLAIN, 12));
        flatSold.setSize(190, 20);
        flatSold.setLocation(250, 250);
        flatSold.setSelectedItem(null);
        cpHomesSold.add(flatSold);

        JLabel domesticQuartLblSold = new JLabel("Domestic Helper Quarters");
        LabelSettings(domesticQuartLblSold);
        domesticQuartLblSold.setLocation(50, 280);
        cpHomesSold.add(domesticQuartLblSold);

        domesticQuartSold = new JComboBox<>(ynList.YNChoice);
        domesticQuartSold.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartSold.setSize(190, 20);
        domesticQuartSold.setLocation(250, 280);
        domesticQuartSold.setSelectedItem(null);
        cpHomesSold.add(domesticQuartSold);

        JLabel otherDetailLblSold = new JLabel("Other");
        LabelSettings(otherDetailLblSold);
        otherDetailLblSold.setLocation(50, 310);
        cpHomesSold.add(otherDetailLblSold);

        otherDetailSold = new JTextArea();
        otherDetailSold.setFont(new Font("Arial", Font.PLAIN, 12));
        otherDetailSold.setSize(250, 70);
        otherDetailSold.setLocation(255, 310);
        otherDetailSold.setLineWrap(true);
        otherDetailSold.setWrapStyleWord(true);
        otherDetailSold.setBorder(BorderFactory.createCompoundBorder(addressSold.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        cpHomesSold.add(otherDetailSold);

        JLabel daysLblSold = new JLabel("Days on the market");
        LabelSettings(daysLblSold);
        daysLblSold.setLocation(50, 390);
        cpHomesSold.add(daysLblSold);

        daysOnMarketSold = new JComboBox<>(numberList.days);
        daysOnMarketSold.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOnMarketSold.setSize(190, 20);
        daysOnMarketSold.setLocation(250, 390);
        daysOnMarketSold.setSelectedItem(null);
        cpHomesSold.add(daysOnMarketSold);

        JLabel listPriceLblSold = new JLabel("List Price");
        LabelSettings(listPriceLblSold);
        listPriceLblSold.setLocation(50, 420);
        cpHomesSold.add(listPriceLblSold);

        JLabel currencyLblSold1 = new JLabel("R");
        currencyLblSold1.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblSold1.setForeground(Color.white);
        currencyLblSold1.setSize(100, 20);
        currencyLblSold1.setLocation(240, 420);
        cpHomesSold.add(currencyLblSold1);

        listPriceSold = new JTextField();
        listPriceSold.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceSold.setSize(120, 20);
        listPriceSold.setLocation(250, 420);
        cpHomesSold.add(listPriceSold);
        CurrencyInputValidation(listPriceSold,cpHomesSold);

        JLabel soldPriceLblSold = new JLabel("Sold Price");
        LabelSettings(soldPriceLblSold);
        soldPriceLblSold.setLocation(50, 450);
        cpHomesSold.add(soldPriceLblSold);

        JLabel currencyLblSold2 = new JLabel("R");
        currencyLblSold2.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblSold2.setForeground(Color.white);
        currencyLblSold2.setSize(100, 20);
        currencyLblSold2.setLocation(240, 450);
        cpHomesSold.add(currencyLblSold2);

        soldPriceSold = new JTextField();
        soldPriceSold.setFont(new Font("Arial", Font.PLAIN, 12));
        soldPriceSold.setSize(120, 20);
        soldPriceSold.setLocation(250, 450);
        cpHomesSold.add(soldPriceSold);
        CurrencyInputValidation(soldPriceSold,cpHomesSold);

        //-----------------------------------------------------------------
        //               "Homes Currently For Sale" Frame
        //-----------------------------------------------------------------
        JFrame homesForSaleFrame = new JFrame();
        homesForSaleFrame.setTitle("Similar homes for sale now");
        homesForSaleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        homesForSaleFrame.setBounds(300, 90, 700, 600);
        homesForSaleFrame.setResizable(false);

        Container cpHomesForSale = homesForSaleFrame.getContentPane();
        cpHomesForSale.setBackground(karisColour1);
        cpHomesForSale.setLayout(null);

        JLabel addressLblForSale = new JLabel("Address");
        LabelSettings(addressLblForSale);
        addressLblForSale.setLocation(50, 15);
        cpHomesForSale.add(addressLblForSale);

        addressForSale = new JTextArea(3,20);
        addressForSale.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        addressForSale.setSize(250, 50);
        addressForSale.setLineWrap(true);
        addressForSale.setWrapStyleWord(true);
        addressForSale.setBorder(BorderFactory.createCompoundBorder(addressForSale.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));
        addressForSale.setLocation(253, 15);
        cpHomesForSale.add(addressForSale);
        AutoCompleteAddress(addressForSale,cpHomesForSale);

        JLabel erfLblForSale = new JLabel("<html>Erf m<sup>2</sup></html>");
        LabelSettings(erfLblForSale);
        erfLblForSale.setLocation(50, 70);
        cpHomesForSale.add(erfLblForSale);

        erfForSale = new JTextField();
        erfForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        erfForSale.setSize(100, 20);
        erfForSale.setLocation(250, 70);
        cpHomesForSale.add(erfForSale);
        NumericInputValidation(erfForSale,cpHomesForSale);

        JLabel livingRoomLblForSale = new JLabel("Living Rooms");
        LabelSettings(livingRoomLblForSale);
        livingRoomLblForSale.setLocation(50, 100);
        cpHomesForSale.add(livingRoomLblForSale);

        livingRoomForSale = new JComboBox<>(numberList.numberChoice);
        livingRoomForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        livingRoomForSale.setSize(190, 20);
        livingRoomForSale.setLocation(250, 100);
        livingRoomForSale.setSelectedItem(null);
        cpHomesForSale.add(livingRoomForSale);

        JLabel bedroomLblForSale = new JLabel("Bedrooms");
        LabelSettings(bedroomLblForSale);
        bedroomLblForSale.setLocation(50, 130);
        cpHomesForSale.add(bedroomLblForSale);

        bedroomForSale = new JComboBox<>(numberList.numberChoice);
        bedroomForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        bedroomForSale.setSize(190, 20);
        bedroomForSale.setLocation(250, 130);
        bedroomForSale.setSelectedItem(null);
        cpHomesForSale.add(bedroomForSale);

        JLabel bathroomLblForSale = new JLabel("Bathrooms");
        LabelSettings(bathroomLblForSale);
        bathroomLblForSale.setLocation(50, 160);
        cpHomesForSale.add(bathroomLblForSale);

        bathroomForSale = new JComboBox<>(numberList.numberChoice);
        bathroomForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        bathroomForSale.setSize(190, 20);
        bathroomForSale.setLocation(250, 160);
        bathroomForSale.setSelectedItem(null);
        cpHomesForSale.add(bathroomForSale);

        JLabel garageLblForSale = new JLabel("Garage");
        LabelSettings(garageLblForSale);
        garageLblForSale.setLocation(50, 190);
        cpHomesForSale.add(garageLblForSale);

        garageForSale = new JComboBox<>(numberList.numberChoice);
        garageForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        garageForSale.setSize(190, 20);
        garageForSale.setLocation(250, 190);
        garageForSale.setSelectedItem(null);
        cpHomesForSale.add(garageForSale);

        JLabel poolLblForSale = new JLabel("Pool");
        LabelSettings(poolLblForSale);
        poolLblForSale.setLocation(50, 220);
        cpHomesForSale.add(poolLblForSale);

        poolForSale = new JComboBox<>(ynList.YNChoice);
        poolForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        poolForSale.setSize(190, 20);
        poolForSale.setLocation(250, 220);
        poolForSale.setSelectedItem(null);
        cpHomesForSale.add(poolForSale);

        JLabel flatLblForSale = new JLabel("Flat");
        LabelSettings(flatLblForSale);
        flatLblForSale.setLocation(50, 250);
        cpHomesForSale.add(flatLblForSale);

        flatForSale = new JComboBox<>(ynList.YNChoice);
        flatForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        flatForSale.setSize(190, 20);
        flatForSale.setLocation(250, 250);
        flatForSale.setSelectedItem(null);
        cpHomesForSale.add(flatForSale);

        JLabel domesticQuartLblForSale = new JLabel("Domestic Helper Quarters");
        LabelSettings(domesticQuartLblForSale);
        domesticQuartLblForSale.setLocation(50, 280);
        cpHomesForSale.add(domesticQuartLblForSale);

        domesticQuartForSale = new JComboBox<>(ynList.YNChoice);
        domesticQuartForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartForSale.setSize(190, 20);
        domesticQuartForSale.setLocation(250, 280);
        domesticQuartForSale.setSelectedItem(null);
        cpHomesForSale.add(domesticQuartForSale);

        JLabel otherDetailLblForSale = new JLabel("Other");
        LabelSettings(otherDetailLblForSale);
        otherDetailLblForSale.setLocation(50, 310);
        cpHomesForSale.add(otherDetailLblForSale);

        otherDetailForSale = new JTextArea();
        otherDetailForSale.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        otherDetailForSale.setSize(250, 70);
        otherDetailForSale.setLineWrap(true);
        otherDetailForSale.setWrapStyleWord(true);
        otherDetailForSale.setBorder(BorderFactory.createCompoundBorder(otherDetailForSale.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));
        otherDetailForSale.setLocation(255, 310);
        cpHomesForSale.add(otherDetailForSale);

        JLabel daysLblForSale = new JLabel("Days on the market");
        LabelSettings(daysLblForSale);
        daysLblForSale.setLocation(50, 390);
        cpHomesForSale.add(daysLblForSale);

        daysOnMarketForSale = new JComboBox<>(numberList.days);
        daysOnMarketForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOnMarketForSale.setSize(190, 20);
        daysOnMarketForSale.setLocation(250, 390);
        daysOnMarketForSale.setSelectedItem(null);
        cpHomesForSale.add(daysOnMarketForSale);

        JLabel listPriceLblForSale = new JLabel("List Price");
        LabelSettings(listPriceLblForSale);
        listPriceLblForSale.setLocation(50, 420);
        cpHomesForSale.add(listPriceLblForSale);

        JLabel currencyLblForSale1 = new JLabel("R");
        currencyLblForSale1.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblForSale1.setForeground(Color.white);
        currencyLblForSale1.setSize(100, 20);
        currencyLblForSale1.setLocation(240, 420);
        cpHomesForSale.add(currencyLblForSale1);

        listPriceForSale = new JTextField();
        listPriceForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceForSale.setSize(120, 20);
        listPriceForSale.setLocation(250, 420);
        cpHomesForSale.add(listPriceForSale);
        CurrencyInputValidation(listPriceForSale,cpHomesForSale);

        //-----------------------------------------------------------------
        //               "Expired Listing" Frame
        //-----------------------------------------------------------------
        JFrame expiredListingsFrame = new JFrame();
        expiredListingsFrame.setTitle("Expired Listings");
        expiredListingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        expiredListingsFrame.setBounds(300, 90, 700, 600);
        expiredListingsFrame.setResizable(false);

        Container cpExpiredListings = expiredListingsFrame.getContentPane();
        cpExpiredListings.setBackground(karisColour1);
        cpExpiredListings.setLayout(null);

        JLabel addressLblExpired = new JLabel("Address");
        LabelSettings(addressLblExpired);
        addressLblExpired.setLocation(50, 15);
        cpExpiredListings.add(addressLblExpired);

        addressExpired = new JTextArea(3,20);
        addressExpired.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        addressExpired.setSize(250, 50);
        addressExpired.setLineWrap(true);
        addressExpired.setWrapStyleWord(true);
        addressExpired.setBorder(BorderFactory.createCompoundBorder(addressExpired.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));
        addressExpired.setLocation(253, 15);
        cpExpiredListings.add(addressExpired);
        AutoCompleteAddress(addressExpired,cpExpiredListings);

        JLabel erfLblExpired = new JLabel("<html>Erf m<sup>2</sup></html>");
        LabelSettings(erfLblExpired);
        erfLblExpired.setLocation(50, 70);
        cpExpiredListings.add(erfLblExpired);

        erfExpired = new JTextField();
        erfExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        erfExpired.setSize(100, 20);
        erfExpired.setLocation(250, 70);
        cpExpiredListings.add(erfExpired);
        NumericInputValidation(erfExpired,cpExpiredListings);

        JLabel livingRoomLblExpired = new JLabel("Living Rooms");
        LabelSettings(livingRoomLblExpired);
        livingRoomLblExpired.setLocation(50, 100);
        cpExpiredListings.add(livingRoomLblExpired);

        livingRoomExpired = new JComboBox<>(numberList.numberChoice);
        livingRoomExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        livingRoomExpired.setSize(190, 20);
        livingRoomExpired.setLocation(250, 100);
        livingRoomExpired.setSelectedItem(null);
        cpExpiredListings.add(livingRoomExpired);

        JLabel bedroomLblExpired = new JLabel("Bedrooms");
        LabelSettings(bedroomLblExpired);
        bedroomLblExpired.setLocation(50, 130);
        cpExpiredListings.add(bedroomLblExpired);

        bedroomExpired = new JComboBox<>(numberList.numberChoice);
        bedroomExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        bedroomExpired.setSize(190, 20);
        bedroomExpired.setLocation(250, 130);
        bedroomExpired.setSelectedItem(null);
        cpExpiredListings.add(bedroomExpired);

        JLabel bathroomLblExpired = new JLabel("Bathrooms");
        LabelSettings(bathroomLblExpired);
        bathroomLblExpired.setLocation(50, 160);
        cpExpiredListings.add(bathroomLblExpired);

        bathroomExpired = new JComboBox<>(numberList.numberChoice);
        bathroomExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        bathroomExpired.setSize(190, 20);
        bathroomExpired.setLocation(250, 160);
        bathroomExpired.setSelectedItem(null);
        cpExpiredListings.add(bathroomExpired);

        JLabel garageLblExpired = new JLabel("Garage");
        LabelSettings(garageLblExpired);
        garageLblExpired.setLocation(50, 190);
        cpExpiredListings.add(garageLblExpired);

        garageExpired = new JComboBox<>(numberList.numberChoice);
        garageExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        garageExpired.setSize(190, 20);
        garageExpired.setLocation(250, 190);
        garageExpired.setSelectedItem(null);
        cpExpiredListings.add(garageExpired);

        JLabel poolLblExpired = new JLabel("Pool");
        LabelSettings(poolLblExpired);
        poolLblExpired.setLocation(50, 220);
        cpExpiredListings.add(poolLblExpired);

        poolExpired = new JComboBox<>(ynList.YNChoice);
        poolExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        poolExpired.setSize(190, 20);
        poolExpired.setLocation(250, 220);
        poolExpired.setSelectedItem(null);
        cpExpiredListings.add(poolExpired);

        JLabel flatLblExpired = new JLabel("Flat");
        LabelSettings(flatLblExpired);
        flatLblExpired.setLocation(50, 250);
        cpExpiredListings.add(flatLblExpired);

        flatExpired = new JComboBox<>(ynList.YNChoice);
        flatExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        flatExpired.setSize(190, 20);
        flatExpired.setLocation(250, 250);
        flatExpired.setSelectedItem(null);
        cpExpiredListings.add(flatExpired);

        JLabel domesticQuartLblExpired = new JLabel("Domestic Helper Quarters");
        LabelSettings(domesticQuartLblExpired);
        domesticQuartLblExpired.setLocation(50, 280);
        cpExpiredListings.add(domesticQuartLblExpired);

        domesticQuartExpired = new JComboBox<>(ynList.YNChoice);
        domesticQuartExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartExpired.setSize(190, 20);
        domesticQuartExpired.setLocation(250, 280);
        domesticQuartExpired.setSelectedItem(null);
        cpExpiredListings.add(domesticQuartExpired);

        JLabel otherDetailLblExpired = new JLabel("Other");
        LabelSettings(otherDetailLblExpired);
        otherDetailLblExpired.setLocation(50, 310);
        cpExpiredListings.add(otherDetailLblExpired);

        otherDetailExpired = new JTextArea();
        otherDetailExpired.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        otherDetailExpired.setSize(250, 70);
        otherDetailExpired.setLineWrap(true);
        otherDetailExpired.setWrapStyleWord(true);
        otherDetailExpired.setBorder(BorderFactory.createCompoundBorder(otherDetailExpired.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));
        otherDetailExpired.setLocation(255, 310);
        cpExpiredListings.add(otherDetailExpired);

        JLabel daysLblExpired = new JLabel("Days on the market");
        LabelSettings(daysLblExpired);
        daysLblExpired.setLocation(50, 390);
        cpExpiredListings.add(daysLblExpired);

        daysOnMarketExpired = new JComboBox<>(numberList.days);
        daysOnMarketExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOnMarketExpired.setSize(190, 20);
        daysOnMarketExpired.setLocation(250, 390);
        daysOnMarketExpired.setSelectedItem(null);
        cpExpiredListings.add(daysOnMarketExpired);

        JLabel listPriceLblExpired = new JLabel("List Price");
        LabelSettings(listPriceLblExpired);
        listPriceLblExpired.setLocation(50, 420);
        cpExpiredListings.add(listPriceLblExpired);

        JLabel currencyLblExpired1 = new JLabel("R");
        currencyLblExpired1.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblExpired1.setForeground(Color.white);
        currencyLblExpired1.setSize(100, 20);
        currencyLblExpired1.setLocation(240, 420);
        cpExpiredListings.add(currencyLblExpired1);

        listPriceExpired = new JTextField();
        listPriceExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceExpired.setSize(120, 20);
        listPriceExpired.setLocation(250, 420);
        cpExpiredListings.add(listPriceExpired);
        CurrencyInputValidation(listPriceExpired,cpExpiredListings);

        //-----------------------------------------------------------------
        //               "Your Property" Frame
        //-----------------------------------------------------------------
        JFrame yourPropertyFrame = new JFrame();
        yourPropertyFrame.setTitle("Your Property");
        yourPropertyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        yourPropertyFrame.setBounds(300, 90, 700, 600);
        yourPropertyFrame.setResizable(false);

        Container cpYourProperty = yourPropertyFrame.getContentPane();
        cpYourProperty.setBackground(karisColour1);
        cpYourProperty.setLayout(null);

        JLabel addressLblYourProp = new JLabel("Address");
        LabelSettings(addressLblYourProp);
        addressLblYourProp.setLocation(50, 15);
        cpYourProperty.add(addressLblYourProp);

        addressYourProp = new JTextArea(3,20);
        addressYourProp.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        addressYourProp.setSize(250,50);
        addressYourProp.setLineWrap(true);
        addressYourProp.setWrapStyleWord(true);
        addressYourProp.setBorder(BorderFactory.createCompoundBorder(addressYourProp.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));
        addressYourProp.setLocation(253, 15);
        cpYourProperty.add(addressYourProp);
        AutoCompleteAddress(addressYourProp,cpYourProperty);

        JLabel erfLblYourProp = new JLabel("<html>Erf m<sup>2</sup></html>");
        LabelSettings(erfLblYourProp);
        erfLblYourProp.setLocation(50, 70);
        cpYourProperty.add(erfLblYourProp);

        erfYourProp = new JTextField();
        erfYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        erfYourProp.setSize(100, 20);
        erfYourProp.setLocation(250, 70);
        NumericInputValidation(erfYourProp,cpYourProperty);
        cpYourProperty.add(erfYourProp);

        JLabel livingRoomLblYourProp = new JLabel("Living Rooms");
        LabelSettings(livingRoomLblYourProp);
        livingRoomLblYourProp.setLocation(50, 100);
        cpYourProperty.add(livingRoomLblYourProp);

        livingRoomYourProp = new JComboBox<>(numberList.numberChoice);
        livingRoomYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        livingRoomYourProp.setSize(190, 20);
        livingRoomYourProp.setLocation(250, 100);
        livingRoomYourProp.setSelectedItem(null);
        cpYourProperty.add(livingRoomYourProp);

        JLabel bedroomLblYourProp = new JLabel("Bedrooms");
        LabelSettings(bedroomLblYourProp);
        bedroomLblYourProp.setLocation(50, 130);
        cpYourProperty.add(bedroomLblYourProp);

        bedroomYourProp = new JComboBox<>(numberList.numberChoice);
        bedroomYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        bedroomYourProp.setSize(190, 20);
        bedroomYourProp.setLocation(250, 130);
        bedroomYourProp.setSelectedItem(null);
        cpYourProperty.add(bedroomYourProp);

        JLabel bathroomLblYourProp = new JLabel("Bathrooms");
        LabelSettings(bathroomLblYourProp);
        bathroomLblYourProp.setLocation(50, 160);
        cpYourProperty.add(bathroomLblYourProp);

        bathroomYourProp = new JComboBox<>(numberList.numberChoice);
        bathroomYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        bathroomYourProp.setSize(190, 20);
        bathroomYourProp.setLocation(250, 160);
        bathroomYourProp.setSelectedItem(null);
        cpYourProperty.add(bathroomYourProp);

        JLabel garageLblYourProp = new JLabel("Garage");
        LabelSettings(garageLblYourProp);
        garageLblYourProp.setLocation(50, 190);
        cpYourProperty.add(garageLblYourProp);

        garageYourProp = new JComboBox<>(numberList.numberChoice);
        garageYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        garageYourProp.setSize(190, 20);
        garageYourProp.setLocation(250, 190);
        garageYourProp.setSelectedItem(null);
        cpYourProperty.add(garageYourProp);

        JLabel poolLblYourProp = new JLabel("Pool");
        LabelSettings(poolLblYourProp);
        poolLblYourProp.setLocation(50, 220);
        cpYourProperty.add(poolLblYourProp);

        poolYourProp = new JComboBox<>(ynList.YNChoice);
        poolYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        poolYourProp.setSize(190, 20);
        poolYourProp.setLocation(250, 220);
        poolYourProp.setSelectedItem(null);
        cpYourProperty.add(poolYourProp);

        JLabel flatLblYourProp = new JLabel("Flat");
        LabelSettings(flatLblYourProp);
        flatLblYourProp.setLocation(50, 250);
        cpYourProperty.add(flatLblYourProp);

        flatYourProp = new JComboBox<>(ynList.YNChoice);
        flatYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        flatYourProp.setSize(190, 20);
        flatYourProp.setLocation(250, 250);
        flatYourProp.setSelectedItem(null);
        cpYourProperty.add(flatYourProp);

        JLabel domesticQuartLblYourProp = new JLabel("Domestic Helper Quarters");
        LabelSettings(domesticQuartLblYourProp);
        domesticQuartLblYourProp.setLocation(50, 280);
        cpYourProperty.add(domesticQuartLblYourProp);

        domesticQuartYourProp = new JComboBox<>(ynList.YNChoice);
        domesticQuartYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartYourProp.setSize(190, 20);
        domesticQuartYourProp.setLocation(250, 280);
        domesticQuartYourProp.setSelectedItem(null);
        cpYourProperty.add(domesticQuartYourProp);

        JLabel otherDetailLblYourProp = new JLabel("Other");
        LabelSettings(otherDetailLblYourProp);
        otherDetailLblYourProp.setLocation(50, 310);
        cpYourProperty.add(otherDetailLblYourProp);

        otherDetailYourProp = new JTextArea();
        otherDetailYourProp.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        otherDetailYourProp.setSize(250, 70);
        otherDetailYourProp.setLineWrap(true);
        otherDetailYourProp.setWrapStyleWord(true);
        otherDetailYourProp.setBorder(BorderFactory.createCompoundBorder(otherDetailYourProp.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));
        otherDetailYourProp.setLocation(255, 310);
        cpYourProperty.add(otherDetailYourProp);

        JLabel daysLblYourProp = new JLabel("Days on the market");
        LabelSettings(daysLblYourProp);
        daysLblYourProp.setLocation(50, 390);
        cpYourProperty.add(daysLblYourProp);

        daysOnMarketYourProp = new JComboBox<>(numberList.days);
        daysOnMarketYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOnMarketYourProp.setSize(190, 20);
        daysOnMarketYourProp.setLocation(250, 390);
        daysOnMarketYourProp.setSelectedItem(null);
        cpYourProperty.add(daysOnMarketYourProp);

        JLabel listPriceLblYourProp = new JLabel("List Price");
        LabelSettings(listPriceLblYourProp);
        listPriceLblYourProp.setLocation(50, 420);
        cpYourProperty.add(listPriceLblYourProp);

        JLabel currencyLblYourProp1 = new JLabel("R");
        currencyLblYourProp1.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblYourProp1.setForeground(Color.white);
        currencyLblYourProp1.setSize(100, 20);
        currencyLblYourProp1.setLocation(240, 420);
        cpYourProperty.add(currencyLblYourProp1);

        listPriceYourProp = new JTextField();
        listPriceYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceYourProp.setSize(120, 20);
        listPriceYourProp.setLocation(250, 420);
        cpYourProperty.add(listPriceYourProp);
        CurrencyInputValidation(listPriceYourProp,cpYourProperty);

        //-----------------------------------------------------------------
        //               "Estimated Value" Frame
        //-----------------------------------------------------------------

        JFrame estimatedValueFrame = new JFrame();
        estimatedValueFrame.setTitle("Estimated & Recommended Listing Values");
        estimatedValueFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        estimatedValueFrame.setBounds(300, 90, 700, 600);
        estimatedValueFrame.setResizable(false);

        Container cpEstimatedValue = estimatedValueFrame.getContentPane();
        cpEstimatedValue.setBackground(karisColour1);
        cpEstimatedValue.setLayout(null);

        JLabel title = new JLabel("Estimated (Average) list value:");
        title.setFont(new Font("Century Gothic", Font.BOLD, 14));
        title.setForeground(Color.white);
        title.setSize(300, 30);
        title.setLocation(50, 15);
        cpEstimatedValue.add(title);

        JLabel currencyLbl = new JLabel("R");
        currencyLbl.setFont(new Font("Arial", Font.BOLD, 14));
        currencyLbl.setForeground(Color.white);
        currencyLbl.setSize(100, 20);
        currencyLbl.setLocation(280, 20);
        cpEstimatedValue.add(currencyLbl);

        JTextField averageValue = new JTextField(String.valueOf(databaseFunctions.avgEstimateValue()));
        averageValue.setFont(new Font("Arial", Font.BOLD, 14));
        averageValue.setSize(150, 30);
        averageValue.setLocation(300, 15);
        averageValue.setEditable(false);
        cpEstimatedValue.add(averageValue);

        JLabel titleMaxValue = new JLabel("Maximum list value:");
        titleMaxValue.setFont(new Font("Century Gothic", Font.BOLD, 14));
        titleMaxValue.setForeground(Color.white);
        titleMaxValue.setSize(300, 30);
        titleMaxValue.setLocation(50, 55);
        cpEstimatedValue.add(titleMaxValue);

        JLabel currencyLblMax = new JLabel("R");
        currencyLblMax.setFont(new Font("Arial", Font.BOLD, 14));
        currencyLblMax.setForeground(Color.white);
        currencyLblMax.setSize(100, 20);
        currencyLblMax.setLocation(280, 60);
        cpEstimatedValue.add(currencyLblMax);

        JTextField maxValue = new JTextField(String.valueOf(databaseFunctions.maxEstimateValue()));
        maxValue.setFont(new Font("Arial", Font.BOLD, 14));
        maxValue.setSize(150, 30);
        maxValue.setLocation(300, 55);
        maxValue.setEditable(false);
        cpEstimatedValue.add(maxValue);

        JLabel titleMinValue = new JLabel("Minimum list value:");
        titleMinValue.setFont(new Font("Century Gothic", Font.BOLD, 14));
        titleMinValue.setForeground(Color.white);
        titleMinValue.setSize(300, 30);
        titleMinValue.setLocation(50, 95);
        cpEstimatedValue.add(titleMinValue);

        JLabel currencyLblMin = new JLabel("R");
        currencyLblMin.setFont(new Font("Arial", Font.BOLD, 14));
        currencyLblMin.setForeground(Color.white);
        currencyLblMin.setSize(100, 20);
        currencyLblMin.setLocation(280, 100);
        cpEstimatedValue.add(currencyLblMin);

        JTextField minValue = new JTextField(String.valueOf(databaseFunctions.minEstimateValue()));
        minValue.setFont(new Font("Arial", Font.BOLD, 14));
        minValue.setSize(150, 30);
        minValue.setLocation(300, 95);
        minValue.setEditable(false);
        cpEstimatedValue.add(minValue);

        JLabel titleRecommendedValue = new JLabel("Recommended Listing Price:");
        titleRecommendedValue.setFont(new Font("Century Gothic", Font.BOLD, 14));
        titleRecommendedValue.setForeground(Color.white);
        titleRecommendedValue.setSize(300, 30);
        titleRecommendedValue.setLocation(250, 150);
        cpEstimatedValue.add(titleRecommendedValue);

        recommendedPriceInput = new JTextField();
        recommendedPriceInput.setFont(new Font("Arial", Font.BOLD, 14));
        recommendedPriceInput.setSize(250, 30);
        recommendedPriceInput.setLocation(230, 175);
        cpEstimatedValue.add(recommendedPriceInput);
        CurrencyInputValidation(recommendedPriceInput,cpEstimatedValue);

        JLabel currencyLblRecommended = new JLabel("R");
        currencyLblRecommended.setFont(new Font("Arial", Font.BOLD, 14));
        currencyLblRecommended.setForeground(Color.white);
        currencyLblRecommended.setSize(100, 20);
        currencyLblRecommended.setLocation(220, 180);
        cpEstimatedValue.add(currencyLblRecommended);



        //-----------------------------------------------------------------
        //                     Main Frame Buttons
        //-----------------------------------------------------------------

        JButton addNewProperty = new JButton("Create a CMA Report");
        addNewProperty.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNewProperty.setMaximumSize(new Dimension(200, 50));
        innerMenuPanel.add(addNewProperty);
        addNewProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homesSoldFrame.setVisible(true);
            }

        });

        //-----------------------------------------------------------------
        //                "Similar Homes Sold" Frame Buttons
        //-----------------------------------------------------------------
        JButton resetButtonSold = new JButton("Reset");
        ResetButtonSettings(resetButtonSold);
                resetButtonSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSoldDetails();
            }
        });
        cpHomesSold.add(resetButtonSold);

        JButton addButtonSold = new JButton("Add");
        AddButtonSettings(addButtonSold);
        addButtonSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                propertiesAdded = propertiesAdded + 1;

                if (propertiesAdded <= 4) {

                    CMAFunctionality.AddingProperties addHome = new CMAFunctionality.AddingProperties();

                    // Get data from the form inputs
                    String address = addressSold.getText();
                    int erf_size = Integer.parseInt(erfSold.getText());
                    int living_room = Integer.parseInt(Objects.requireNonNull(livingRoomSold.getSelectedItem()).toString());
                    int bedroom = Integer.parseInt(Objects.requireNonNull(bedroomSold.getSelectedItem()).toString());
                    int bathroom = Integer.parseInt(Objects.requireNonNull(bathroomSold.getSelectedItem()).toString());
                    int garage = Integer.parseInt(Objects.requireNonNull(garageSold.getSelectedItem()).toString());
                    boolean pool = Objects.requireNonNull(poolSold.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    boolean flat = Objects.requireNonNull(flatSold.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    boolean domestic_quar = Objects.requireNonNull(domesticQuartSold.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    String other_detail = otherDetailSold.getText();
                    int days_on_market = Integer.parseInt(Objects.requireNonNull(daysOnMarketSold.getSelectedItem()).toString());
                    double list_price = Double.parseDouble(listPriceSold.getText());
                    double sold_price = Double.parseDouble(soldPriceSold.getText());

                    String addHomeSoldMessage = addHome.addHomesSold(address, erf_size, living_room, bedroom, bathroom, garage, pool, flat, domestic_quar, other_detail, days_on_market, list_price, sold_price);

                    JOptionPane.showMessageDialog(null, addHomeSoldMessage);
                    clearSoldDetails();
                }

                if (propertiesAdded == 4) {
                    addButtonSold.setEnabled(false);
                    addButtonSold.setToolTipText("Maximum 4 properties have been added");
                }
            }
        });
        cpHomesSold.add(addButtonSold);

        JButton nextButtonSold = new JButton("Next");
        NextButtonSettings(nextButtonSold);
        nextButtonSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homesSoldFrame.setVisible(false);
                homesForSaleFrame.setVisible(true);
            }
        });
        cpHomesSold.add(nextButtonSold);

        //-----------------------------------------------------------------
        //               "Homes Currently For Sale" Frame Buttons
        //-----------------------------------------------------------------

        JButton resetButtonForSale = new JButton("Reset");
        ResetButtonSettings(resetButtonForSale);
        resetButtonForSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForSaleDetails();
            }
        });
        cpHomesForSale.add(resetButtonForSale);

        JButton addButtonForSale = new JButton("Add");
        AddButtonSettings(addButtonForSale);
        addButtonForSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                propertiesAdded = propertiesAdded + 1;

                if (propertiesAdded <= 4) {

                    CMAFunctionality.AddingProperties addHome = new CMAFunctionality.AddingProperties();

                    String address = addressForSale.getText();
                    int erf_size = Integer.parseInt(erfForSale.getText());
                    int living_room = Integer.parseInt(Objects.requireNonNull(livingRoomForSale.getSelectedItem()).toString());
                    int bedroom = Integer.parseInt(Objects.requireNonNull(bedroomForSale.getSelectedItem()).toString());
                    int bathroom = Integer.parseInt(Objects.requireNonNull(bathroomForSale.getSelectedItem()).toString());
                    int garage = Integer.parseInt(Objects.requireNonNull(garageForSale.getSelectedItem()).toString());
                    boolean pool = Objects.requireNonNull(poolForSale.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    boolean flat = Objects.requireNonNull(flatForSale.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    boolean domestic_quar = Objects.requireNonNull(domesticQuartForSale.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    String other_detail = otherDetailForSale.getText();
                    int days_on_market = Integer.parseInt(Objects.requireNonNull(daysOnMarketForSale.getSelectedItem()).toString());
                    double list_price = Double.parseDouble(listPriceForSale.getText());

                    String addHomeForSaleMessage = addHome.addHomesForSale(address, erf_size, living_room, bedroom, bathroom, garage, pool, flat, domestic_quar, other_detail, days_on_market, list_price);

                    JOptionPane.showMessageDialog(null, addHomeForSaleMessage);
                    clearForSaleDetails();
                }

                if (propertiesAdded == 4) {
                    addButtonForSale.setEnabled(false);
                    addButtonForSale.setToolTipText("Maximum 4 properties have been added");

                }
            }
        });
        cpHomesForSale.add(addButtonForSale);

        JButton nextButtonForSale = new JButton("Next");
        NextButtonSettings(nextButtonForSale);
        nextButtonForSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                homesForSaleFrame.setVisible(false);
                expiredListingsFrame.setVisible(true);

            }
        });
        cpHomesForSale.add(nextButtonForSale);

        //-----------------------------------------------------------------
        //               "Expired Listings" Frame Buttons
        //-----------------------------------------------------------------

        JButton resetButtonExpired = new JButton("Reset");
        ResetButtonSettings(resetButtonExpired);
        resetButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clearExpiredDetails();
            }
        });
        cpExpiredListings.add(resetButtonExpired);

        JButton addButtonExpired = new JButton("Add");
        AddButtonSettings(addButtonExpired);
        addButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                propertiesAdded = propertiesAdded + 1;

                if (propertiesAdded <= 4) {

                    CMAFunctionality.AddingProperties addHome = new CMAFunctionality.AddingProperties();

                    // Get data from the form inputs
                    String address = addressExpired.getText();
                    int erf_size = Integer.parseInt(erfExpired.getText());
                    int living_room = Integer.parseInt(Objects.requireNonNull(livingRoomExpired.getSelectedItem()).toString());
                    int bedroom = Integer.parseInt(Objects.requireNonNull(bedroomExpired.getSelectedItem()).toString());
                    int bathroom = Integer.parseInt(Objects.requireNonNull(bathroomExpired.getSelectedItem()).toString());
                    int garage = Integer.parseInt(Objects.requireNonNull(garageExpired.getSelectedItem()).toString());
                    boolean pool = Objects.requireNonNull(poolExpired.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    boolean flat = Objects.requireNonNull(flatExpired.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    boolean domestic_quar = Objects.requireNonNull(domesticQuartExpired.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                    String other_detail = otherDetailExpired.getText();
                    int days_on_market = Integer.parseInt(Objects.requireNonNull(daysOnMarketExpired.getSelectedItem()).toString());
                    double list_price = Double.parseDouble(listPriceExpired.getText());


                    String addHomeExpiredMessage = addHome.addHomesExpired(address, erf_size, living_room, bedroom, bathroom, garage, pool, flat, domestic_quar, other_detail, days_on_market, list_price);

                    JOptionPane.showMessageDialog(null, addHomeExpiredMessage);
                    clearExpiredDetails();
                }

                if (propertiesAdded == 4) {
                    addButtonExpired.setEnabled(false);
                    addButtonExpired.setToolTipText("Maximum 4 properties have been added");

                }

            }
        });
        cpExpiredListings.add(addButtonExpired);

        JButton nextButtonExpired = new JButton("Next");
        NextButtonSettings(nextButtonExpired);
        nextButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                expiredListingsFrame.setVisible(false);
                yourPropertyFrame.setVisible(true);

            }
        });
        cpExpiredListings.add(nextButtonExpired);


        //-----------------------------------------------------------------
        //               "Your Property" Frame Buttons
        //-----------------------------------------------------------------

        JButton resetButtonYourProp = new JButton("Reset");
        ResetButtonSettings(resetButtonYourProp);
        resetButtonYourProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearYourPropertyDetails();
            }
        });
        cpYourProperty.add(resetButtonYourProp);

        JButton addButtonYourProp = new JButton("Add");
        AddButtonSettings(addButtonYourProp);
        addButtonYourProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CMAFunctionality.AddingProperties addHome = new CMAFunctionality.AddingProperties();

                // Get data from the form inputs
                String address = addressYourProp.getText();
                int erf_size = Integer.parseInt(erfYourProp.getText());
                int living_room = Integer.parseInt(Objects.requireNonNull(livingRoomYourProp.getSelectedItem()).toString());
                int bedroom = Integer.parseInt(Objects.requireNonNull(bedroomYourProp.getSelectedItem()).toString());
                int bathroom = Integer.parseInt(Objects.requireNonNull(bathroomYourProp.getSelectedItem()).toString());
                int garage = Integer.parseInt(Objects.requireNonNull(garageYourProp.getSelectedItem()).toString());
                boolean pool = Objects.requireNonNull(poolYourProp.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                boolean flat = Objects.requireNonNull(flatYourProp.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                boolean domestic_quar = Objects.requireNonNull(domesticQuartYourProp.getSelectedItem()).toString().equalsIgnoreCase("Yes");
                String other_detail = otherDetailYourProp.getText();
                int days_on_market = Integer.parseInt(Objects.requireNonNull(daysOnMarketYourProp.getSelectedItem()).toString());
                double list_price = Double.parseDouble(listPriceYourProp.getText());

                String addButtonYourPropMessage = addHome.addYourProp(address, erf_size, living_room, bedroom, bathroom, garage, pool, flat, domestic_quar, other_detail, days_on_market, list_price);

                JOptionPane.showMessageDialog(null, addButtonYourPropMessage);
                addButtonYourProp.setEnabled(false);
                addButtonYourProp.setToolTipText("Only one property can be added");

            }
        });
        cpYourProperty.add(addButtonYourProp);

        JButton nextButtonYourProp = new JButton("Next");
        NextButtonSettings(nextButtonYourProp);
        nextButtonYourProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yourPropertyFrame.setVisible(false);
                estimatedValueFrame.setVisible(true);
            }
        });
        cpYourProperty.add(nextButtonYourProp);


        //-----------------------------------------------------------------
        //               "Estimated Value" Frame Buttons
        //-----------------------------------------------------------------

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setFont(new Font("Arial", Font.BOLD, 12));
        generateReportButton.setSize(150, 50);
        generateReportButton.setLocation(50, 500);
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recommendedPriceText = recommendedPriceInput.getText();

                WordReportGenerator reportGenerator = new WordReportGenerator();

                reportGenerator.setRecommendedPrice(recommendedPriceText);

                String reportGeneratedMessage = reportGenerator.wordReportGenerator();

                JOptionPane.showMessageDialog(null, reportGeneratedMessage);
                estimatedValueFrame.setVisible(false);

            }
        });
        cpEstimatedValue.add(generateReportButton);

        mainFrame.setVisible(true);

    }

    //-----------------------------------------------------------------
    //                      Methods
    //-----------------------------------------------------------------


    public void clearSoldDetails() {

        addressSold.setText("");
        erfSold.setText("");
        livingRoomSold.setSelectedIndex(-1);
        bedroomSold.setSelectedIndex(-1);
        bathroomSold.setSelectedIndex(-1);
        garageSold.setSelectedIndex(-1);
        poolSold.setSelectedIndex(-1);
        flatSold.setSelectedIndex(-1);
        domesticQuartSold.setSelectedIndex(-1);
        daysOnMarketSold.setSelectedItem(null);
        otherDetailSold.setText("");
        listPriceSold.setText("");
        soldPriceSold.setText("");
    }

    public void clearForSaleDetails() {

        addressForSale.setText("");
        erfForSale.setText("");
        livingRoomForSale.setSelectedIndex(-1);
        bedroomForSale.setSelectedIndex(-1);
        bathroomForSale.setSelectedIndex(-1);
        garageForSale.setSelectedIndex(-1);
        poolForSale.setSelectedIndex(-1);
        flatForSale.setSelectedIndex(-1);
        domesticQuartForSale.setSelectedIndex(-1);
        daysOnMarketForSale.setSelectedItem(null);
        otherDetailForSale.setText("");
        listPriceForSale.setText("");

    }

    public void clearExpiredDetails() {

        addressExpired.setText("");
        erfExpired.setText("");
        livingRoomExpired.setSelectedIndex(-1);
        bedroomExpired.setSelectedIndex(-1);
        bathroomExpired.setSelectedIndex(-1);
        garageExpired.setSelectedIndex(-1);
        poolExpired.setSelectedIndex(-1);
        flatExpired.setSelectedIndex(-1);
        domesticQuartExpired.setSelectedIndex(-1);
        daysOnMarketExpired.setSelectedItem(null);
        otherDetailExpired.setText("");
        listPriceExpired.setText("");
    }

    public void clearYourPropertyDetails() {

        addressYourProp.setText("");
        erfYourProp.setText("");
        livingRoomYourProp.setSelectedIndex(-1);
        bedroomYourProp.setSelectedIndex(-1);
        bathroomYourProp.setSelectedIndex(-1);
        garageYourProp.setSelectedIndex(-1);
        poolYourProp.setSelectedIndex(-1);
        flatYourProp.setSelectedIndex(-1);
        domesticQuartYourProp.setSelectedIndex(-1);
        daysOnMarketYourProp.setSelectedItem(null);
        otherDetailYourProp.setText("");
        listPriceYourProp.setText("");
    }

    public void ResetButtonSettings(JButton button) {

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setSize(100, 50);
        button.setLocation(50, 510);

    };

    public void AddButtonSettings(JButton button) {

        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setSize(100, 50);
        button.setLocation(180, 510);

    };

    public void NextButtonSettings (JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setSize(100, 50);
        button.setLocation(310, 510);
    }

    public void LabelSettings (JLabel label) {
        label.setFont(new Font("Century Gothic", Font.BOLD, 12));
        label.setForeground(Color.white);
        label.setSize(180, 30);
    }

    public void NumericInputValidation(JTextField textField, Container container) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                // check if input is a number
                if ((keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9) ||
                        keyCode == KeyEvent.VK_BACK_SPACE ||
                        keyCode == KeyEvent.VK_DELETE ||
                        keyCode == KeyEvent.VK_LEFT ||
                        keyCode == KeyEvent.VK_RIGHT ||
                        keyCode == KeyEvent.VK_SHIFT) {
                    textField.setEditable(true);

                } else {
                    textField.setEditable(false);
                    JOptionPane.showMessageDialog(container, "Please enter numeric values only.");
                    textField.setText("");
                }
            }
        });

    }

    public void CurrencyInputValidation(JTextField textField, Container container) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                double keyCode = e.getKeyCode();

                // check if input is a number
                if ((keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9) ||
                        keyCode == KeyEvent.VK_BACK_SPACE ||
                        keyCode == KeyEvent.VK_DELETE ||
                        keyCode == KeyEvent.VK_LEFT ||
                        keyCode == KeyEvent.VK_RIGHT ||
                        keyCode == KeyEvent.VK_PERIOD ||
                        keyCode == KeyEvent.VK_SHIFT) {
                    textField.setEditable(true);

                } else {
                    textField.setEditable(false);
                    JOptionPane.showMessageDialog(container, "Please enter numeric values only. Use a period (.) to separate rands and cents.");
                    textField.setText("");
                }
            }
        });

    }

    public void AutoCompleteAddress(JTextArea addressTextArea, Container container ) {
        // Create a new list model for this specific addressTextArea
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> suggestionList = new JList<>(listModel);

        // Create a new JScrollPane for the current addressTextArea
        JScrollPane suggestionScrollPane = new JScrollPane(suggestionList);
        suggestionScrollPane.setSize(250, 100);
        suggestionScrollPane.setLocation(250, 70);
        suggestionScrollPane.setVisible(false);

        // Add the JScrollPane to the container
        container.add(suggestionScrollPane);

        // Key listener for the addressTextArea
        addressTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = addressTextArea.getText();

                if (input.length() > 2) {
                    fetchSuggestions(input, listModel, suggestionScrollPane);
                    suggestionScrollPane.setVisible(true);
                } else {
                    suggestionScrollPane.setVisible(false);
                }
            }
        });

        // Add mouse listener to handle selection from the suggestion list
        suggestionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = suggestionList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    String selectedAddress = listModel.getElementAt(index);
                    addressTextArea.setText(selectedAddress);
                    listModel.clear();
                    suggestionScrollPane.setVisible(false);
                }
            }
        });


    }

    public void fetchSuggestions(String input, DefaultListModel<String> listModel, JScrollPane suggestionScrollPane) {

        String apiKey = "AIzaSyA3cYBiZ3VbNPB8E4-uqkVUH_EuWR2yon0";
        String requestURL = String.format("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%s&key=%s",
                URLEncoder.encode(input, StandardCharsets.UTF_8), apiKey);

        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            reader.close();

            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray predictionsArray = jsonObject.get("predictions").getAsJsonArray();

            listModel.clear();
            for (int i = 0; i < predictionsArray.size(); i++) {
                JsonObject prediction = predictionsArray.get(i).getAsJsonObject();
                listModel.addElement(prediction.get("description").getAsString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }





}
