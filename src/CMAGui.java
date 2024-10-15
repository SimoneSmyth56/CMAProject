import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CMAGui {
    JTextArea addressForSale, addressSold, otherDetailForSale, otherDetailSold, addressExpired, otherDetailExpired, addressYourProp, otherDetailYourProp ;
    JTextField erfForSale, listPriceForSale, erfSold, listPriceSold, soldPriceSold, erfExpired, listPriceExpired, erfYourProp, listPriceYourProp;
    JComboBox<String> livingRoomForSale, bedroomForSale, bathroomForSale, garageForSale, poolForSale, flatForSale, domesticQuartForSale, livingRoomSold, bedroomSold, bathroomSold, garageSold, poolSold, flatSold, domesticQuartSold, livingRoomExpired, bedroomExpired, bathroomExpired, garageExpired, poolExpired, flatExpired, domesticQuartExpired, livingRoomYourProp, bedroomYourProp, bathroomYourProp, garageYourProp, poolYourProp, flatYourProp, domesticQuartYourProp;
    JComboBox<Integer> daysOnMarketSold, daysOnMarketForSale, daysOnMarketExpired, daysOnMarketYourProp;

    CMAFunctionality.numberList numberList = new CMAFunctionality.numberList();
    CMAFunctionality.YNList ynList = new CMAFunctionality.YNList();
    DatabaseFunctions databaseFunctions = new DatabaseFunctions();

    Color karisColour1 = new Color(40,128,129);

    public CMAGui() {

        //-----------------------------------------------------------------
        //                       Main Frame
        //-----------------------------------------------------------------
        Border blackline = BorderFactory.createLineBorder(Color.black);
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(800, 600));

        JPanel topLabelPanel = new JPanel();
        topLabelPanel.setPreferredSize(new Dimension(800, 50));
        topLabelPanel.setBackground(karisColour1);

        JPanel leftMenuPanel = new JPanel();
        leftMenuPanel.setPreferredSize(new Dimension(220, 500));
        leftMenuPanel.setBorder(blackline);

        JPanel innerMenuPanel = new JPanel();
        innerMenuPanel.setLayout(new BoxLayout(innerMenuPanel, BoxLayout.Y_AXIS));
        innerMenuPanel.setPreferredSize(new Dimension(200, 480));
        innerMenuPanel.setBackground(karisColour1);
        innerMenuPanel.setBorder(blackline);
        leftMenuPanel.add(innerMenuPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(520, 500));
        centerPanel.setBorder(blackline);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(800, 50));
        bottomPanel.setBackground(karisColour1);


        // Container panel to hold the task table and file handling side by side
        JPanel containerPanel = new JPanel();
        containerPanel.add(topLabelPanel, BorderLayout.NORTH);
        containerPanel.add(leftMenuPanel, BorderLayout.WEST);
        containerPanel.add(centerPanel, BorderLayout.EAST);
        containerPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(containerPanel);


        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Comparative Market Analysis");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.white);
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


        JLabel welcomeMessage = new JLabel("Welcome");
        welcomeMessage.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeMessage.setForeground(Color.WHITE);
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeMessage.setMaximumSize(new Dimension(200, 80));
        innerMenuPanel.add(welcomeMessage);

        //Similar homes sold frame-----------------------------------------------------
        JFrame homesSoldFrame = new JFrame();
        homesSoldFrame.setTitle("Similar homes recently sold");
        homesSoldFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        homesSoldFrame.setBounds(300, 90, 700,600);
        homesSoldFrame.setResizable(false);

        Container cpHomesSold = homesSoldFrame.getContentPane();
        cpHomesSold.setLayout(null);

        JLabel addressLblSold = new JLabel("Address");
        addressLblSold.setFont(new Font("Arial", Font.BOLD, 14));
        addressLblSold.setSize(300, 30);
        addressLblSold.setLocation(50, 15);
        cpHomesSold.add(addressLblSold);

        addressSold = new JTextArea();
        addressSold.setFont(new Font("Arial", Font.PLAIN, 12));
        addressSold.setSize(250,50);
        addressSold.setLocation(255, 15);
        cpHomesSold.add(addressSold);

        JLabel erfLblSold = new JLabel("Erf m2");
        erfLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        erfLblSold.setSize(100, 20);
        erfLblSold.setLocation(50, 70);
        cpHomesSold.add(erfLblSold);

        erfSold = new JTextField();
        erfSold.setFont(new Font("Arial", Font.PLAIN, 12));
        erfSold.setSize(100, 20);
        erfSold.setLocation(250, 70);
        cpHomesSold.add(erfSold);

        JLabel livingRoomLblSold = new JLabel("Living Rooms");
        livingRoomLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        livingRoomLblSold.setSize(100, 20);
        livingRoomLblSold.setLocation(50, 100);
        cpHomesSold.add(livingRoomLblSold);

        livingRoomSold = new JComboBox<>(numberList.numberChoice);
        livingRoomSold.setFont(new Font("Arial", Font.PLAIN, 12));
        livingRoomSold.setSize(190, 20);
        livingRoomSold.setLocation(250, 100);
        livingRoomSold.setSelectedItem(null);
        cpHomesSold.add(livingRoomSold);

        JLabel bedroomLblSold = new JLabel("Bedrooms");
        bedroomLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        bedroomLblSold.setSize(100, 20);
        bedroomLblSold.setLocation(50, 130);
        cpHomesSold.add(bedroomLblSold);

        bedroomSold = new JComboBox<>(numberList.numberChoice);
        bedroomSold.setFont(new Font("Arial", Font.PLAIN, 12));
        bedroomSold.setSize(190, 20);
        bedroomSold.setLocation(250, 130);
        bedroomSold.setSelectedItem(null);
        cpHomesSold.add(bedroomSold);

        JLabel bathroomLblSold = new JLabel("Bathrooms");
        bathroomLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        bathroomLblSold.setSize(100, 20);
        bathroomLblSold.setLocation(50, 160);
        cpHomesSold.add(bathroomLblSold);

        bathroomSold = new JComboBox<>(numberList.numberChoice);
        bathroomSold.setFont(new Font("Arial", Font.PLAIN, 12));
        bathroomSold.setSize(190, 20);
        bathroomSold.setLocation(250, 160);
        bathroomSold.setSelectedItem(null);
        cpHomesSold.add(bathroomSold);

        JLabel garageLblSold = new JLabel("Garage");
        garageLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        garageLblSold.setSize(100, 20);
        garageLblSold.setLocation(50, 190);
        cpHomesSold.add(garageLblSold);

        garageSold = new JComboBox<>(numberList.numberChoice);
        garageSold.setFont(new Font("Arial", Font.PLAIN, 12));
        garageSold.setSize(190, 20);
        garageSold.setLocation(250, 190);
        garageSold.setSelectedItem(null);
        cpHomesSold.add(garageSold);

        JLabel poolLblSold = new JLabel("Pool");
        poolLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        poolLblSold.setSize(100, 20);
        poolLblSold.setLocation(50, 220);
        cpHomesSold.add(poolLblSold);

        poolSold = new JComboBox<>(ynList.YNChoice);
        poolSold.setFont(new Font("Arial", Font.PLAIN, 12));
        poolSold.setSize(190, 20);
        poolSold.setLocation(250, 220);
        poolSold.setSelectedItem(null);
        cpHomesSold.add(poolSold);

        JLabel flatLblSold = new JLabel("Flat");
        flatLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        flatLblSold.setSize(100, 20);
        flatLblSold.setLocation(50, 250);
        cpHomesSold.add(flatLblSold);

        flatSold = new JComboBox<>(ynList.YNChoice);
        flatSold.setFont(new Font("Arial", Font.PLAIN, 12));
        flatSold.setSize(190, 20);
        flatSold.setLocation(250, 250);
        flatSold.setSelectedItem(null);
        cpHomesSold.add(flatSold);

        JLabel domesticQuartLblSold = new JLabel("Domestic Helper Quarters");
        domesticQuartLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        domesticQuartLblSold.setSize(150, 20);
        domesticQuartLblSold.setLocation(50, 280);
        cpHomesSold.add(domesticQuartLblSold);

        domesticQuartSold = new JComboBox<>(ynList.YNChoice);
        domesticQuartSold.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartSold.setSize(190, 20);
        domesticQuartSold.setLocation(250, 280);
        domesticQuartSold.setSelectedItem(null);
        cpHomesSold.add(domesticQuartSold);

        JLabel otherDetailLblSold = new JLabel("Other");
        otherDetailLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        otherDetailLblSold.setSize(400, 30);
        otherDetailLblSold.setLocation(50, 310);
        cpHomesSold.add(otherDetailLblSold);

        otherDetailSold= new JTextArea();
        otherDetailSold.setFont(new Font("Arial", Font.PLAIN, 12));
        otherDetailSold.setSize(250,70);
        otherDetailSold.setLocation(255, 310);
        cpHomesSold.add(otherDetailSold);

        JLabel daysLblSold = new JLabel("Days on the market");
        daysLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        daysLblSold.setSize(150, 20);
        daysLblSold.setLocation(50, 390);
        cpHomesSold.add(daysLblSold);

        daysOnMarketSold = new JComboBox<>(numberList.days);
        daysOnMarketSold.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOnMarketSold.setSize(190, 20);
        daysOnMarketSold.setLocation(250, 390);
        daysOnMarketSold.setSelectedItem(null);
        cpHomesSold.add(daysOnMarketSold);

        JLabel listPriceLblSold = new JLabel("List Price");
        listPriceLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        listPriceLblSold.setSize(100, 20);
        listPriceLblSold.setLocation(50, 420);
        cpHomesSold.add(listPriceLblSold);

        JLabel currencyLblSold1 = new JLabel("R");
        currencyLblSold1.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblSold1.setSize(100, 20);
        currencyLblSold1.setLocation(240, 420);
        cpHomesSold.add(currencyLblSold1);

        listPriceSold = new JTextField();
        listPriceSold.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceSold.setSize(120, 20);
        listPriceSold.setLocation(250, 420);
        cpHomesSold.add(listPriceSold);

        JLabel soldPriceLblSold = new JLabel("Sold Price");
        soldPriceLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        soldPriceLblSold.setSize(100, 20);
        soldPriceLblSold.setLocation(50, 450);
        cpHomesSold.add(soldPriceLblSold);

        JLabel currencyLblSold2 = new JLabel("R");
        currencyLblSold2.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblSold2.setSize(100, 20);
        currencyLblSold2.setLocation(240, 450);
        cpHomesSold.add(currencyLblSold2);

        soldPriceSold = new JTextField();
        soldPriceSold.setFont(new Font("Arial", Font.PLAIN, 12));
        soldPriceSold.setSize(120, 20);
        soldPriceSold.setLocation(250, 450);
        cpHomesSold.add(soldPriceSold);


        //Similar homes for sale frame-----------------------------------------------------
        JFrame homesForSaleFrame = new JFrame();
        homesForSaleFrame.setTitle("Similar homes for sale now");
        homesForSaleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        homesForSaleFrame.setBounds(300, 90, 700,600);
        homesForSaleFrame.setResizable(false);


        Container cpHomesForSale = homesForSaleFrame.getContentPane();
        cpHomesForSale.setLayout(null);

        JLabel addressLblForSale = new JLabel("Address");
        addressLblForSale.setFont(new Font("Arial", Font.BOLD, 14));
        addressLblForSale.setSize(300, 30);
        addressLblForSale.setLocation(50, 15);
        cpHomesForSale.add(addressLblForSale);

        addressForSale = new JTextArea();
        addressForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        addressForSale.setSize(250,50);
        addressForSale.setLocation(255, 15);
        cpHomesForSale.add(addressForSale);

        JLabel erfLblForSale = new JLabel("Erf m2");
        erfLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        erfLblForSale.setSize(100, 20);
        erfLblForSale.setLocation(50, 70);
        cpHomesForSale.add(erfLblForSale);

        erfForSale = new JTextField();
        erfForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        erfForSale.setSize(100, 20);
        erfForSale.setLocation(250, 70);
        cpHomesForSale.add(erfForSale);

        JLabel livingRoomLblForSale = new JLabel("Living Rooms");
        livingRoomLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        livingRoomLblForSale.setSize(100, 20);
        livingRoomLblForSale.setLocation(50, 100);
        cpHomesForSale.add(livingRoomLblForSale);

        livingRoomForSale = new JComboBox<>(numberList.numberChoice);
        livingRoomForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        livingRoomForSale.setSize(190, 20);
        livingRoomForSale.setLocation(250, 100);
        livingRoomForSale.setSelectedItem(null);
        cpHomesForSale.add(livingRoomForSale);

        JLabel bedroomLblForSale = new JLabel("Bedrooms");
        bedroomLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        bedroomLblForSale.setSize(100, 20);
        bedroomLblForSale.setLocation(50, 130);
        cpHomesForSale.add(bedroomLblForSale);

        bedroomForSale = new JComboBox<>(numberList.numberChoice);
        bedroomForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        bedroomForSale.setSize(190, 20);
        bedroomForSale.setLocation(250, 130);
        bedroomForSale.setSelectedItem(null);
        cpHomesForSale.add(bedroomForSale);

        JLabel bathroomLblForSale = new JLabel("Bathrooms");
        bathroomLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        bathroomLblForSale.setSize(100, 20);
        bathroomLblForSale.setLocation(50, 160);
        cpHomesForSale.add(bathroomLblForSale);

        bathroomForSale = new JComboBox<>(numberList.numberChoice);
        bathroomForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        bathroomForSale.setSize(190, 20);
        bathroomForSale.setLocation(250, 160);
        bathroomForSale.setSelectedItem(null);
        cpHomesForSale.add(bathroomForSale);

        JLabel garageLblForSale = new JLabel("Garage");
        garageLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        garageLblForSale.setSize(100, 20);
        garageLblForSale.setLocation(50, 190);
        cpHomesForSale.add(garageLblForSale);

        garageForSale = new JComboBox<>(numberList.numberChoice);
        garageForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        garageForSale.setSize(190, 20);
        garageForSale.setLocation(250, 190);
        garageForSale.setSelectedItem(null);
        cpHomesForSale.add(garageForSale);

        JLabel poolLblForSale = new JLabel("Pool");
        poolLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        poolLblForSale.setSize(100, 20);
        poolLblForSale.setLocation(50, 220);
        cpHomesForSale.add(poolLblForSale);

        poolForSale = new JComboBox<>(ynList.YNChoice);
        poolForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        poolForSale.setSize(190, 20);
        poolForSale.setLocation(250, 220);
        poolForSale.setSelectedItem(null);
        cpHomesForSale.add(poolForSale);

        JLabel flatLblForSale = new JLabel("Flat");
        flatLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        flatLblForSale.setSize(100, 20);
        flatLblForSale.setLocation(50, 250);
        cpHomesForSale.add(flatLblForSale);

        flatForSale = new JComboBox<>(ynList.YNChoice);
        flatForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        flatForSale.setSize(190, 20);
        flatForSale.setLocation(250, 250);
        flatForSale.setSelectedItem(null);
        cpHomesForSale.add(flatForSale);

        JLabel domesticQuartLblForSale = new JLabel("Domestic Helper Quarters");
        domesticQuartLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        domesticQuartLblForSale.setSize(150, 20);
        domesticQuartLblForSale.setLocation(50, 280);
        cpHomesForSale.add(domesticQuartLblForSale);

        domesticQuartForSale = new JComboBox<>(ynList.YNChoice);
        domesticQuartForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartForSale.setSize(190, 20);
        domesticQuartForSale.setLocation(250, 280);
        domesticQuartForSale.setSelectedItem(null);
        cpHomesForSale.add(domesticQuartForSale);

        JLabel otherDetailLblForSale = new JLabel("Other");
        otherDetailLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        otherDetailLblForSale.setSize(400, 30);
        otherDetailLblForSale.setLocation(50, 310);
        cpHomesForSale.add(otherDetailLblForSale);

        otherDetailForSale = new JTextArea();
        otherDetailForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        otherDetailForSale.setSize(250,70);
        otherDetailForSale.setLocation(255, 310);
        cpHomesForSale.add(otherDetailForSale);

        JLabel daysLblForSale = new JLabel("Days on the market");
        daysLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        daysLblForSale.setSize(150, 20);
        daysLblForSale.setLocation(50, 390);
        cpHomesForSale.add(daysLblForSale);

        daysOnMarketForSale = new JComboBox<>(numberList.days);
        daysOnMarketForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOnMarketForSale.setSize(190, 20);
        daysOnMarketForSale.setLocation(250, 390);
        daysOnMarketForSale.setSelectedItem(null);
        cpHomesForSale.add(daysOnMarketForSale);

        JLabel listPriceLblForSale = new JLabel("List Price");
        listPriceLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        listPriceLblForSale.setSize(100, 20);
        listPriceLblForSale.setLocation(50, 420);
        cpHomesForSale.add(listPriceLblForSale);

        JLabel currencyLblForSale1 = new JLabel("R");
        currencyLblForSale1.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblForSale1.setSize(100, 20);
        currencyLblForSale1.setLocation(240, 420);
        cpHomesForSale.add(currencyLblForSale1);

        listPriceForSale = new JTextField();
        listPriceForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceForSale.setSize(120, 20);
        listPriceForSale.setLocation(250, 420);
        cpHomesForSale.add(listPriceForSale);



        //Expired/Old listings frame-----------------------------------------------------
        JFrame expiredListingsFrame = new JFrame();
        expiredListingsFrame.setTitle("Expired/Old Listings");
        expiredListingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        expiredListingsFrame.setBounds(300, 90, 700,600);
        expiredListingsFrame.setResizable(false);


        Container cpExpiredListings = expiredListingsFrame.getContentPane();
        cpExpiredListings.setLayout(null);

        JLabel addressLblExpired = new JLabel("Address");
        addressLblExpired.setFont(new Font("Arial", Font.BOLD, 14));
        addressLblExpired.setSize(300, 30);
        addressLblExpired.setLocation(50, 15);
        cpExpiredListings.add(addressLblExpired);

        addressExpired = new JTextArea();
        addressExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        addressExpired.setSize(250,50);
        addressExpired.setLocation(255, 15);
        cpExpiredListings.add(addressExpired);

        JLabel erfLblExpired = new JLabel("Erf m2");
        erfLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        erfLblExpired.setSize(100, 20);
        erfLblExpired.setLocation(50, 70);
        cpExpiredListings.add(erfLblExpired);

        erfExpired = new JTextField();
        erfExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        erfExpired.setSize(100, 20);
        erfExpired.setLocation(250, 70);
        cpExpiredListings.add(erfExpired);

        JLabel livingRoomLblExpired = new JLabel("Living Rooms");
        livingRoomLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        livingRoomLblExpired.setSize(100, 20);
        livingRoomLblExpired.setLocation(50, 100);
        cpExpiredListings.add(livingRoomLblExpired);

        livingRoomExpired= new JComboBox<>(numberList.numberChoice);
        livingRoomExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        livingRoomExpired.setSize(190, 20);
        livingRoomExpired.setLocation(250, 100);
        livingRoomExpired.setSelectedItem(null);
        cpExpiredListings.add(livingRoomExpired);

        JLabel bedroomLblExpired = new JLabel("Bedrooms");
        bedroomLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        bedroomLblExpired.setSize(100, 20);
        bedroomLblExpired.setLocation(50, 130);
        cpExpiredListings.add(bedroomLblExpired);

        bedroomExpired = new JComboBox<>(numberList.numberChoice);
        bedroomExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        bedroomExpired.setSize(190, 20);
        bedroomExpired.setLocation(250, 130);
        bedroomExpired.setSelectedItem(null);
        cpExpiredListings.add(bedroomExpired);

        JLabel bathroomLblExpired = new JLabel("Bathrooms");
        bathroomLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        bathroomLblExpired.setSize(100, 20);
        bathroomLblExpired.setLocation(50, 160);
        cpExpiredListings.add(bathroomLblExpired);

        bathroomExpired = new JComboBox<>(numberList.numberChoice);
        bathroomExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        bathroomExpired.setSize(190, 20);
        bathroomExpired.setLocation(250, 160);
        bathroomExpired.setSelectedItem(null);
        cpExpiredListings.add(bathroomExpired);

        JLabel garageLblExpired = new JLabel("Garage");
        garageLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        garageLblExpired.setSize(100, 20);
        garageLblExpired.setLocation(50, 190);
        cpExpiredListings.add(garageLblExpired);

        garageExpired = new JComboBox<>(numberList.numberChoice);
        garageExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        garageExpired.setSize(190, 20);
        garageExpired.setLocation(250, 190);
        garageExpired.setSelectedItem(null);
        cpExpiredListings.add(garageExpired);

        JLabel poolLblExpired = new JLabel("Pool");
        poolLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        poolLblExpired.setSize(100, 20);
        poolLblExpired.setLocation(50, 220);
        cpExpiredListings.add(poolLblExpired);

        poolExpired = new JComboBox<>(ynList.YNChoice);
        poolExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        poolExpired.setSize(190, 20);
        poolExpired.setLocation(250, 220);
        poolExpired.setSelectedItem(null);
        cpExpiredListings.add(poolExpired);

        JLabel flatLblExpired = new JLabel("Flat");
        flatLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        flatLblExpired.setSize(100, 20);
        flatLblExpired.setLocation(50, 250);
        cpExpiredListings.add(flatLblExpired);

        flatExpired = new JComboBox<>(ynList.YNChoice);
        flatExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        flatExpired.setSize(190, 20);
        flatExpired.setLocation(250, 250);
        flatExpired.setSelectedItem(null);
        cpExpiredListings.add(flatExpired);

        JLabel domesticQuartLblExpired = new JLabel("Domestic Helper Quarters");
        domesticQuartLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        domesticQuartLblExpired.setSize(150, 20);
        domesticQuartLblExpired.setLocation(50, 280);
        cpExpiredListings.add(domesticQuartLblExpired);

        domesticQuartExpired = new JComboBox<>(ynList.YNChoice);
        domesticQuartExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartExpired.setSize(190, 20);
        domesticQuartExpired.setLocation(250, 280);
        domesticQuartExpired.setSelectedItem(null);
        cpExpiredListings.add(domesticQuartExpired);

        JLabel otherDetailLblExpired = new JLabel("Other");
        otherDetailLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        otherDetailLblExpired.setSize(400, 30);
        otherDetailLblExpired.setLocation(50, 310);
        cpExpiredListings.add(otherDetailLblExpired);

        otherDetailExpired = new JTextArea();
        otherDetailExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        otherDetailExpired.setSize(250,70);
        otherDetailExpired.setLocation(255, 310);
        cpExpiredListings.add(otherDetailExpired);

        JLabel daysLblExpired = new JLabel("Days on the market");
        daysLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        daysLblExpired.setSize(150, 20);
        daysLblExpired.setLocation(50, 390);
        cpExpiredListings.add(daysLblExpired);

        daysOnMarketExpired = new JComboBox<>(numberList.days);
        daysOnMarketExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOnMarketExpired.setSize(190, 20);
        daysOnMarketExpired.setLocation(250, 390);
        daysOnMarketExpired.setSelectedItem(null);
        cpExpiredListings.add(daysOnMarketExpired);

        JLabel listPriceLblExpired= new JLabel("List Price");
        listPriceLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        listPriceLblExpired.setSize(100, 20);
        listPriceLblExpired.setLocation(50, 420);
        cpExpiredListings.add(listPriceLblExpired);

        JLabel currencyLblExpired1 = new JLabel("R");
        currencyLblExpired1.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblExpired1.setSize(100, 20);
        currencyLblExpired1.setLocation(240, 420);
        cpExpiredListings.add(currencyLblExpired1);

        listPriceExpired = new JTextField();
        listPriceExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceExpired.setSize(120, 20);
        listPriceExpired.setLocation(250, 420);
        cpExpiredListings.add(listPriceExpired);

        //Your Property frame-----------------------------------------------------
        JFrame yourPropertyFrame = new JFrame();
        yourPropertyFrame.setTitle("Your Property");
        yourPropertyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        yourPropertyFrame.setBounds(300, 90, 700,600);
        yourPropertyFrame.setResizable(false);


        Container cpYourProperty = yourPropertyFrame.getContentPane();
        cpYourProperty.setLayout(null);

        JLabel addressLblYourProp = new JLabel("Address");
        addressLblYourProp.setFont(new Font("Arial", Font.BOLD, 14));
        addressLblYourProp.setSize(300, 30);
        addressLblYourProp.setLocation(50, 15);
        cpYourProperty.add(addressLblYourProp);

        addressYourProp = new JTextArea();
        addressYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        addressYourProp.setSize(250,50);
        addressYourProp.setLocation(255, 15);
        cpYourProperty.add(addressYourProp);

        JLabel erfLblYourProp = new JLabel("Erf m2");
        erfLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        erfLblYourProp.setSize(100, 20);
        erfLblYourProp.setLocation(50, 70);
        cpYourProperty.add(erfLblYourProp);

        erfYourProp = new JTextField();
        erfYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        erfYourProp.setSize(100, 20);
        erfYourProp.setLocation(250, 70);
        cpYourProperty.add(erfYourProp);

        JLabel livingRoomLblYourProp = new JLabel("Living Rooms");
        livingRoomLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        livingRoomLblYourProp.setSize(100, 20);
        livingRoomLblYourProp.setLocation(50, 100);
        cpYourProperty.add(livingRoomLblYourProp);

        livingRoomYourProp = new JComboBox<>(numberList.numberChoice);
        livingRoomYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        livingRoomYourProp.setSize(190, 20);
        livingRoomYourProp.setLocation(250, 100);
        livingRoomYourProp.setSelectedItem(null);
        cpYourProperty.add(livingRoomYourProp);

        JLabel bedroomLblYourProp = new JLabel("Bedrooms");
        bedroomLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        bedroomLblYourProp.setSize(100, 20);
        bedroomLblYourProp.setLocation(50, 130);
        cpYourProperty.add(bedroomLblYourProp);

        bedroomYourProp = new JComboBox<>(numberList.numberChoice);
        bedroomYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        bedroomYourProp.setSize(190, 20);
        bedroomYourProp.setLocation(250, 130);
        bedroomYourProp.setSelectedItem(null);
        cpYourProperty.add(bedroomYourProp);

        JLabel bathroomLblYourProp = new JLabel("Bathrooms");
        bathroomLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        bathroomLblYourProp.setSize(100, 20);
        bathroomLblYourProp.setLocation(50, 160);
        cpYourProperty.add(bathroomLblYourProp);

        bathroomYourProp = new JComboBox<>(numberList.numberChoice);
        bathroomYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        bathroomYourProp.setSize(190, 20);
        bathroomYourProp.setLocation(250, 160);
        bathroomYourProp.setSelectedItem(null);
        cpYourProperty.add(bathroomYourProp);

        JLabel garageLblYourProp = new JLabel("Garage");
        garageLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        garageLblYourProp.setSize(100, 20);
        garageLblYourProp.setLocation(50, 190);
        cpYourProperty.add(garageLblYourProp);

        garageYourProp = new JComboBox<>(numberList.numberChoice);
        garageYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        garageYourProp.setSize(190, 20);
        garageYourProp.setLocation(250, 190);
        garageYourProp.setSelectedItem(null);
        cpYourProperty.add(garageYourProp);

        JLabel poolLblYourProp = new JLabel("Pool");
        poolLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        poolLblYourProp.setSize(100, 20);
        poolLblYourProp.setLocation(50, 220);
        cpYourProperty.add(poolLblYourProp);

        poolYourProp = new JComboBox<>(ynList.YNChoice);
        poolYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        poolYourProp.setSize(190, 20);
        poolYourProp.setLocation(250, 220);
        poolYourProp.setSelectedItem(null);
        cpYourProperty.add(poolYourProp);

        JLabel flatLblYourProp = new JLabel("Flat");
        flatLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        flatLblYourProp.setSize(100, 20);
        flatLblYourProp.setLocation(50, 250);
        cpYourProperty.add(flatLblYourProp);

        flatYourProp = new JComboBox<>(ynList.YNChoice);
        flatYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        flatYourProp.setSize(190, 20);
        flatYourProp.setLocation(250, 250);
        flatYourProp.setSelectedItem(null);
        cpYourProperty.add(flatYourProp);

        JLabel domesticQuartLblYourProp = new JLabel("Domestic Helper Quarters");
        domesticQuartLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        domesticQuartLblYourProp.setSize(150, 20);
        domesticQuartLblYourProp.setLocation(50, 280);
        cpYourProperty.add(domesticQuartLblYourProp);

        domesticQuartYourProp = new JComboBox<>(ynList.YNChoice);
        domesticQuartYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartYourProp.setSize(190, 20);
        domesticQuartYourProp.setLocation(250, 280);
        domesticQuartYourProp.setSelectedItem(null);
        cpYourProperty.add(domesticQuartYourProp);

        JLabel otherDetailLblYourProp = new JLabel("Other");
        otherDetailLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        otherDetailLblYourProp.setSize(400, 30);
        otherDetailLblYourProp.setLocation(50, 310);
        cpYourProperty.add(otherDetailLblYourProp);

        otherDetailYourProp = new JTextArea();
        otherDetailYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        otherDetailYourProp.setSize(250,70);
        otherDetailYourProp.setLocation(255, 310);
        cpYourProperty.add(otherDetailYourProp);

        JLabel daysLblYourProp = new JLabel("Days on the market");
        daysLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        daysLblYourProp.setSize(150, 20);
        daysLblYourProp.setLocation(50, 390);
        cpYourProperty.add(daysLblYourProp);

        daysOnMarketYourProp = new JComboBox<>(numberList.days);
        daysOnMarketYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOnMarketYourProp.setSize(190, 20);
        daysOnMarketYourProp.setLocation(250, 390);
        daysOnMarketYourProp.setSelectedItem(null);
        cpYourProperty.add(daysOnMarketYourProp);

        JLabel listPriceLblYourProp = new JLabel("List Price");
        listPriceLblYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        listPriceLblYourProp.setSize(100, 20);
        listPriceLblYourProp.setLocation(50, 420);
        cpYourProperty.add(listPriceLblYourProp);

        JLabel currencyLblYourProp1 = new JLabel("R");
        currencyLblYourProp1.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblYourProp1.setSize(100, 20);
        currencyLblYourProp1.setLocation(240, 420);
        cpYourProperty.add(currencyLblYourProp1);

        listPriceYourProp = new JTextField();
        listPriceYourProp.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceYourProp.setSize(120, 20);
        listPriceYourProp.setLocation(250, 420);
        cpYourProperty.add(listPriceYourProp);

        //Estimated Value Frame ----------------------------------------

        JFrame estimatedValueFrame = new JFrame();
        estimatedValueFrame.setTitle("Estimated Value");
        estimatedValueFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        estimatedValueFrame.setBounds(300, 90, 700,600);
        estimatedValueFrame.setResizable(false);


        Container cpEstimatedValue = estimatedValueFrame.getContentPane();
        cpEstimatedValue.setLayout(null);

        JLabel title = new JLabel("Your home's estimated value:");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setSize(300, 30);
        title.setLocation(50, 15);
        cpEstimatedValue.add(title);

        JTextField averageValue = new JTextField(String.valueOf(databaseFunctions.avgEstimateValue()));
        averageValue.setFont(new Font("Arial", Font.PLAIN, 12));
        averageValue.setSize(150, 20);
        averageValue.setLocation(250, 390);
        cpEstimatedValue.add(averageValue);


        //Main Frame Buttons---------------------------------------------

        JButton homeButton = new JButton("Home");
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.setFont(new Font("Arial", Font.BOLD, 16));
        homeButton.setMaximumSize(new Dimension(200, 90));
        innerMenuPanel.add(homeButton);

        JButton addNewProperty = new JButton("Add new property");
        addNewProperty.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNewProperty.setMaximumSize(new Dimension(200, 90));
        innerMenuPanel.add(addNewProperty);
        addNewProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homesSoldFrame.setVisible(true);
            }

        });

        JButton previousReports = new JButton("Previous reports");
        previousReports.setAlignmentX(Component.CENTER_ALIGNMENT);
        previousReports.setMaximumSize(new Dimension(200, 90));
        innerMenuPanel.add(previousReports);


        //Homes Sold Frame buttons ------------------------------------------------------------------------
        JButton resetButtonSold = new JButton("Reset");
        resetButtonSold.setFont(new Font("Arial", Font.BOLD, 12));
        resetButtonSold.setSize(100, 20);
        resetButtonSold.setLocation(50, 500);
        resetButtonSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSoldDetails();
            }
        });
        cpHomesSold.add(resetButtonSold);

        JButton addButtonSold = new JButton("Add");
        addButtonSold.setFont(new Font("Arial", Font.BOLD, 12));
        addButtonSold.setSize(100, 20);
        addButtonSold.setLocation(180, 500);
        addButtonSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get data from the form inputs
                String address = addressSold.getText();
                int erf_size = Integer.parseInt(erfSold.getText());
                int living_room = Integer.parseInt(livingRoomSold.getSelectedItem().toString());
                int bedroom = Integer.parseInt(bedroomSold.getSelectedItem().toString());
                int bathroom = Integer.parseInt(bathroomSold.getSelectedItem().toString());
                int garage = Integer.parseInt(garageSold.getSelectedItem().toString());
                boolean pool = poolSold.getSelectedItem().toString().equalsIgnoreCase("Yes");
                boolean flat = flatSold.getSelectedItem().toString().equalsIgnoreCase("Yes");
                boolean domestic_quar = domesticQuartSold.getSelectedItem().toString().equalsIgnoreCase("Yes");
                String other_detail = otherDetailSold.getText();
                int days_on_market = Integer.parseInt(daysOnMarketSold.getSelectedItem().toString());
                double list_price = Double.parseDouble(listPriceSold.getText());
                double sold_price = Double.parseDouble(soldPriceSold.getText());

                CMAFunctionality.AddingProperties addHome = new CMAFunctionality.AddingProperties();

                String addHomeSoldMessage = addHome.addHomesSold(address,erf_size,living_room,bedroom,bathroom,garage,pool,flat,domestic_quar,other_detail,days_on_market,list_price,sold_price);

                JOptionPane.showMessageDialog(null, addHomeSoldMessage);
                clearSoldDetails();
            }
        });
        cpHomesSold.add(addButtonSold);

        JButton nextButtonSold = new JButton("Next");
        nextButtonSold.setFont(new Font("Arial", Font.BOLD, 12));
        nextButtonSold.setSize(100, 20);
        nextButtonSold.setLocation(440, 500);
        nextButtonSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homesSoldFrame.setVisible(false);
                homesForSaleFrame.setVisible(true);

            }
        });
        cpHomesSold.add(nextButtonSold);

        //------------------------------------------------

        //Homes For Sale Frame Buttons
        JButton resetButtonForSale = new JButton("Reset");
        resetButtonForSale.setFont(new Font("Arial", Font.BOLD, 12));
        resetButtonForSale.setSize(100, 20);
        resetButtonForSale.setLocation(50, 500);
        resetButtonForSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForSaleDetails();

            }
        });
        cpHomesForSale.add(resetButtonForSale);

        JButton addButtonForSale = new JButton("Add");
        addButtonForSale.setFont(new Font("Arial", Font.BOLD, 12));
        addButtonForSale.setSize(100, 20);
        addButtonForSale.setLocation(180, 500);
        addButtonForSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = addressForSale.getText();
                int erf_size = Integer.parseInt(erfForSale.getText());
                int living_room = Integer.parseInt(livingRoomForSale.getSelectedItem().toString());
                int bedroom = Integer.parseInt(bedroomForSale.getSelectedItem().toString());
                int bathroom = Integer.parseInt(bathroomForSale.getSelectedItem().toString());
                int garage = Integer.parseInt(garageForSale.getSelectedItem().toString());
                boolean pool = poolForSale.getSelectedItem().toString().equalsIgnoreCase("Yes");
                boolean flat = flatForSale.getSelectedItem().toString().equalsIgnoreCase("Yes");
                boolean domestic_quar = domesticQuartForSale.getSelectedItem().toString().equalsIgnoreCase("Yes");
                String other_detail = otherDetailForSale.getText();
                int days_on_market = Integer.parseInt(daysOnMarketForSale.getSelectedItem().toString());
                double list_price = Double.parseDouble(listPriceForSale.getText());

                CMAFunctionality.AddingProperties addHome = new CMAFunctionality.AddingProperties();

                String addHomeForSaleMessage = addHome.addHomesForSale(address,erf_size,living_room,bedroom,bathroom,garage,pool,flat,domestic_quar,other_detail,days_on_market,list_price);

                JOptionPane.showMessageDialog(null, addHomeForSaleMessage);
                clearForSaleDetails();


            }
        });
        cpHomesForSale.add(addButtonForSale);

        JButton nextButtonForSale = new JButton("Next");
        nextButtonForSale.setFont(new Font("Arial", Font.BOLD, 12));
        nextButtonForSale.setSize(100, 20);
        nextButtonForSale.setLocation(310, 500);
        nextButtonForSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homesForSaleFrame.setVisible(false);
                expiredListingsFrame.setVisible(true);

            }
        });
        cpHomesForSale.add(nextButtonForSale);

        // Expired Listings Frame buttons ------------------------------------------------------------------------
        JButton resetButtonExpired = new JButton("Reset");
        resetButtonExpired.setFont(new Font("Arial", Font.BOLD, 12));
        resetButtonExpired.setSize(100, 20);
        resetButtonExpired.setLocation(50, 500);
        resetButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearExpiredDetails();

            }
        });
        cpExpiredListings.add(resetButtonExpired);

        JButton addButtonExpired = new JButton("Add");
        addButtonExpired.setFont(new Font("Arial", Font.BOLD, 12));
        addButtonExpired.setSize(100, 20);
        addButtonExpired.setLocation(180, 500);
        addButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get data from the form inputs
                String address = addressExpired.getText();
                int erf_size = Integer.parseInt(erfExpired.getText());
                int living_room = Integer.parseInt(livingRoomExpired.getSelectedItem().toString());
                int bedroom = Integer.parseInt(bedroomExpired.getSelectedItem().toString());
                int bathroom = Integer.parseInt(bathroomExpired.getSelectedItem().toString());
                int garage = Integer.parseInt(garageExpired.getSelectedItem().toString());
                boolean pool = poolExpired.getSelectedItem().toString().equalsIgnoreCase("Yes");
                boolean flat = flatExpired.getSelectedItem().toString().equalsIgnoreCase("Yes");
                boolean domestic_quar = domesticQuartExpired.getSelectedItem().toString().equalsIgnoreCase("Yes");
                String other_detail = otherDetailExpired.getText();
                int days_on_market = Integer.parseInt(daysOnMarketExpired.getSelectedItem().toString());
                double list_price = Double.parseDouble(listPriceExpired.getText());


                CMAFunctionality.AddingProperties addHome = new CMAFunctionality.AddingProperties();

                String addHomeExpiredMessage = addHome.addHomesExpired(address,erf_size,living_room,bedroom,bathroom,garage,pool,flat,domestic_quar,other_detail,days_on_market,list_price);

                JOptionPane.showMessageDialog(null, addHomeExpiredMessage);
                clearExpiredDetails();
            }
        });
        cpExpiredListings.add(addButtonExpired);

        JButton nextButtonExpired = new JButton("Next");
        nextButtonExpired.setFont(new Font("Arial", Font.BOLD, 12));
        nextButtonExpired.setSize(100, 20);
        nextButtonExpired.setLocation(440, 500);
        nextButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expiredListingsFrame.setVisible(false);
                yourPropertyFrame.setVisible(true);

            }
        });
        cpExpiredListings.add(nextButtonExpired);

        //------------------------------------------------

        // Your Property Frame buttons ------------------------------------------------------------------------
        JButton resetButtonYourProp = new JButton("Reset");
        resetButtonYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        resetButtonYourProp.setSize(100, 20);
        resetButtonYourProp.setLocation(50, 500);
        resetButtonYourProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        cpYourProperty.add(resetButtonYourProp);

        JButton addButtonYourProp = new JButton("Add");
        addButtonYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        addButtonYourProp.setSize(100, 20);
        addButtonYourProp.setLocation(180, 500);
        addButtonYourProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get data from the form inputs
                String address = addressYourProp.getText();
                int erf_size = Integer.parseInt(erfYourProp.getText());
                int living_room = Integer.parseInt(livingRoomYourProp.getSelectedItem().toString());
                int bedroom = Integer.parseInt(bedroomYourProp.getSelectedItem().toString());
                int bathroom = Integer.parseInt(bathroomYourProp.getSelectedItem().toString());
                int garage = Integer.parseInt(garageYourProp.getSelectedItem().toString());
                boolean pool = poolYourProp.getSelectedItem().toString().equalsIgnoreCase("Yes");
                boolean flat = flatYourProp.getSelectedItem().toString().equalsIgnoreCase("Yes");
                boolean domestic_quar = domesticQuartYourProp.getSelectedItem().toString().equalsIgnoreCase("Yes");
                String other_detail = otherDetailYourProp.getText();
                int days_on_market = Integer.parseInt(daysOnMarketYourProp.getSelectedItem().toString());
                double list_price = Double.parseDouble(listPriceYourProp.getText());


                CMAFunctionality.AddingProperties addHome = new CMAFunctionality.AddingProperties();

                String addButtonYourPropMessage = addHome.addYourProp(address,erf_size,living_room,bedroom,bathroom,garage,pool,flat,domestic_quar,other_detail,days_on_market,list_price);

                JOptionPane.showMessageDialog(null, addButtonYourPropMessage);
                addButtonYourProp.setEnabled(false);
                estimatedValueFrame.setVisible(true);

            }
        });
        cpYourProperty.add(addButtonYourProp);

        JButton nextButtonYourProp = new JButton("Next");
        nextButtonYourProp.setFont(new Font("Arial", Font.BOLD, 12));
        nextButtonYourProp.setSize(100, 20);
        nextButtonYourProp.setLocation(440, 500);
        nextButtonYourProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        cpYourProperty.add(nextButtonYourProp);

        //-----------------------------------------

        mainFrame.setVisible(true);

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
        daysOnMarketForSale.setSelectedIndex(-1);
        otherDetailForSale.setText("");
        listPriceForSale.setText("");

    }

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
        daysOnMarketSold.setSelectedItem(-1);
        otherDetailSold.setText("");
        listPriceSold.setText("");
        soldPriceSold.setText("");
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
        daysOnMarketExpired.setSelectedItem(-1);
        otherDetailExpired.setText("");
        listPriceExpired.setText("");
    }
}
