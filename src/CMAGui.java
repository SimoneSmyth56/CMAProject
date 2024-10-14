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
    CMAFunctionality.numberList numberList = new CMAFunctionality.numberList();
    CMAFunctionality.YNList ynList = new CMAFunctionality.YNList();

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

        BufferedImage logoImage = null;
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

        JTextArea addressSold= new JTextArea();
        addressSold.setFont(new Font("Arial", Font.PLAIN, 12));
        addressSold.setSize(250,50);
        addressSold.setLocation(255, 15);
        cpHomesSold.add(addressSold);

        JLabel erfLblSold = new JLabel("Erf m2");
        erfLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        erfLblSold.setSize(100, 20);
        erfLblSold.setLocation(50, 70);
        cpHomesSold.add(erfLblSold);

        JTextField erfSold = new JTextField();
        erfSold.setFont(new Font("Arial", Font.PLAIN, 12));
        erfSold.setSize(100, 20);
        erfSold.setLocation(250, 70);
        cpHomesSold.add(erfSold);

        JLabel livingRoomLblSold = new JLabel("Living Rooms");
        livingRoomLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        livingRoomLblSold.setSize(100, 20);
        livingRoomLblSold.setLocation(50, 100);
        cpHomesSold.add(livingRoomLblSold);

        JComboBox<String> livingRoomSold = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> bedroomSold = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> bathroomSold = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> garageSold = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> poolSold = new JComboBox<>(ynList.YNChoice);
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

        JComboBox<String> flatSold = new JComboBox<>(ynList.YNChoice);
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

        JComboBox<String> domesticQuartSold = new JComboBox<>(ynList.YNChoice);
        domesticQuartSold.setFont(new Font("Arial", Font.PLAIN, 12));
        domesticQuartSold.setSize(190, 20);
        domesticQuartSold.setLocation(250, 250);
        domesticQuartSold.setSelectedItem(null);
        cpHomesSold.add(domesticQuartSold);

        JLabel otherDetailLblSold = new JLabel("Other");
        otherDetailLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        otherDetailLblSold.setSize(400, 30);
        otherDetailLblSold.setLocation(50, 310);
        cpHomesSold.add(otherDetailLblSold);

        JTextArea otherDetailSold= new JTextArea();
        otherDetailSold.setFont(new Font("Arial", Font.PLAIN, 12));
        otherDetailSold.setSize(250,70);
        otherDetailSold.setLocation(255, 310);
        cpHomesSold.add(otherDetailSold);

        JLabel daysLblSold = new JLabel("Days on the market");
        daysLblSold.setFont(new Font("Arial", Font.BOLD, 12));
        daysLblSold.setSize(150, 20);
        daysLblSold.setLocation(50, 390);
        cpHomesSold.add(daysLblSold);

        JComboBox<String> daysSold = new JComboBox<>(numberList.numberChoice);
        daysSold.setFont(new Font("Arial", Font.PLAIN, 12));
        daysSold.setSize(190, 20);
        daysSold.setLocation(250, 390);
        daysSold.setSelectedItem(null);
        cpHomesSold.add(daysSold);

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

        JTextField listPriceSold = new JTextField();
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

        JTextField soldPriceSold = new JTextField();
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

        JTextArea addressForSale = new JTextArea();
        addressForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        addressForSale.setSize(250,50);
        addressForSale.setLocation(255, 15);
        cpHomesForSale.add(addressForSale);

        JLabel erfLblForSale = new JLabel("Erf m2");
        erfLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        erfLblForSale.setSize(100, 20);
        erfLblForSale.setLocation(50, 70);
        cpHomesForSale.add(erfLblForSale);

        JTextField erfForSale = new JTextField();
        erfForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        erfForSale.setSize(100, 20);
        erfForSale.setLocation(250, 70);
        cpHomesForSale.add(erfForSale);

        JLabel livingRoomLblForSale = new JLabel("Living Rooms");
        livingRoomLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        livingRoomLblForSale.setSize(100, 20);
        livingRoomLblForSale.setLocation(50, 100);
        cpHomesForSale.add(livingRoomLblForSale);

        JComboBox<String> livingRoomForSale = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> bedroomForSale = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> bathroomForSale = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> garageForSale = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> poolForSale = new JComboBox<>(ynList.YNChoice);
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

        JComboBox<String> flatForSale = new JComboBox<>(ynList.YNChoice);
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

        JComboBox<String> domesticQuartForSale = new JComboBox<>(ynList.YNChoice);
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

        JTextArea otherDetailForSale = new JTextArea();
        otherDetailForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        otherDetailForSale.setSize(250,70);
        otherDetailForSale.setLocation(255, 310);
        cpHomesForSale.add(otherDetailForSale);

        JLabel daysLblForSale = new JLabel("Days on the market");
        daysLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        daysLblForSale.setSize(150, 20);
        daysLblForSale.setLocation(50, 390);
        cpHomesForSale.add(daysLblForSale);

        JComboBox<String> daysOptionForSale = new JComboBox<>(numberList.numberChoice);
        daysOptionForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        daysOptionForSale.setSize(190, 20);
        daysOptionForSale.setLocation(250, 390);
        daysOptionForSale.setSelectedItem(null);
        cpHomesForSale.add(daysOptionForSale);

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

        JTextField listPriceForSale = new JTextField();
        listPriceForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceForSale.setSize(120, 20);
        listPriceForSale.setLocation(250, 420);
        cpHomesForSale.add(listPriceForSale);

        JLabel soldPriceLblForSale = new JLabel("Sold Price");
        soldPriceLblForSale.setFont(new Font("Arial", Font.BOLD, 12));
        soldPriceLblForSale.setSize(100, 20);
        soldPriceLblForSale.setLocation(50, 450);
        cpHomesForSale.add(soldPriceLblForSale);

        JLabel currencyLblForSale2 = new JLabel("R");
        currencyLblForSale2.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblForSale2.setSize(100, 20);
        currencyLblForSale2.setLocation(240, 450);
        cpHomesForSale.add(currencyLblForSale2);

        JTextField soldPriceForSale = new JTextField();
        soldPriceForSale.setFont(new Font("Arial", Font.PLAIN, 12));
        soldPriceForSale.setSize(120, 20);
        soldPriceForSale.setLocation(250, 450);
        cpHomesForSale.add(soldPriceForSale);

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

        JTextArea addressExpired = new JTextArea();
        addressExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        addressExpired.setSize(250,50);
        addressExpired.setLocation(255, 15);
        cpExpiredListings.add(addressExpired);

        JLabel erfLblExpired = new JLabel("Erf m2");
        erfLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        erfLblExpired.setSize(100, 20);
        erfLblExpired.setLocation(50, 70);
        cpExpiredListings.add(erfLblExpired);

        JTextField erfExpired = new JTextField();
        erfExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        erfExpired.setSize(100, 20);
        erfExpired.setLocation(250, 70);
        cpExpiredListings.add(erfExpired);

        JLabel livingRoomLblExpired = new JLabel("Living Rooms");
        livingRoomLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        livingRoomLblExpired.setSize(100, 20);
        livingRoomLblExpired.setLocation(50, 100);
        cpExpiredListings.add(livingRoomLblExpired);

        JComboBox<String> livingRoomExpired= new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> bedroomExpired = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> bathroomExpired = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> garageExpired = new JComboBox<>(numberList.numberChoice);
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

        JComboBox<String> poolExpired = new JComboBox<>(ynList.YNChoice);
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

        JComboBox<String> flatExpired = new JComboBox<>(ynList.YNChoice);
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

        JComboBox<String> domesticQuartExpired = new JComboBox<>(ynList.YNChoice);
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

        JTextArea otherDetailExpired = new JTextArea();
        otherDetailExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        otherDetailExpired.setSize(250,70);
        otherDetailExpired.setLocation(255, 310);
        cpExpiredListings.add(otherDetailExpired);

        JLabel daysLblExpired = new JLabel("Days on the market");
        daysLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        daysLblExpired.setSize(150, 20);
        daysLblExpired.setLocation(50, 390);
        cpExpiredListings.add(daysLblExpired);

        JComboBox<String> daysExpired = new JComboBox<>(numberList.numberChoice);
        daysExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        daysExpired.setSize(190, 20);
        daysExpired.setLocation(250, 390);
        daysExpired.setSelectedItem(null);
        cpExpiredListings.add(daysExpired);

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

        JTextField listPriceExpired = new JTextField();
        listPriceExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        listPriceExpired.setSize(120, 20);
        listPriceExpired.setLocation(250, 420);
        cpExpiredListings.add(listPriceExpired);

        JLabel soldPriceLblExpired = new JLabel("Sold Price");
        soldPriceLblExpired.setFont(new Font("Arial", Font.BOLD, 12));
        soldPriceLblExpired.setSize(100, 20);
        soldPriceLblExpired.setLocation(50, 450);
        cpExpiredListings.add(soldPriceLblExpired);

        JLabel currencyLblExpired2 = new JLabel("R");
        currencyLblExpired2.setFont(new Font("Arial", Font.BOLD, 12));
        currencyLblExpired2.setSize(100, 20);
        currencyLblExpired2.setLocation(240, 450);
        cpExpiredListings.add(currencyLblExpired2);

        JTextField soldPriceExpired = new JTextField();
        soldPriceExpired.setFont(new Font("Arial", Font.PLAIN, 12));
        soldPriceExpired.setSize(120, 20);
        soldPriceExpired.setLocation(250, 450);
        cpExpiredListings.add(soldPriceExpired);


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
                addressSold.setText("");
                erfSold.setText("");
                livingRoomSold.setSelectedIndex(-1);
                bedroomSold.setSelectedIndex(-1);
                bathroomSold.setSelectedIndex(-1);
                garageSold.setSelectedIndex(-1);
                poolSold.setSelectedIndex(-1);
                flatSold.setSelectedIndex(-1);
                domesticQuartSold.setSelectedIndex(-1);
                daysSold.setSelectedIndex(-1);
                otherDetailSold.setText("");
                listPriceSold.setText("");
                soldPriceSold.setText("");
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
                int livingRooms = Integer.parseInt(livingRoomSold.getSelectedItem().toString());
                int bedrooms = Integer.parseInt(bedroomSold.getSelectedItem().toString());
                int bathrooms = Integer.parseInt(bathroomSold.getSelectedItem().toString());
                int garages = Integer.parseInt(garageSold.getSelectedItem().toString());
                String pool = poolSold.getSelectedItem().toString();
                String flat = flatSold.getSelectedItem().toString();
                String domesticQuarters = domesticQuartSold.getSelectedItem().toString();
                String otherDetails = otherDetailSold.getText();
                int daysOnMarket = Integer.parseInt(daysSold.getSelectedItem().toString());
                double listPrice = Double.parseDouble(listPriceSold.getText());
                double soldPrice = Double.parseDouble(soldPriceSold.getText());

            }
        });
        cpHomesSold.add(addButtonSold);

        JButton uploadButtonSold = new JButton("Upload Image");
        uploadButtonSold.setFont(new Font("Arial", Font.BOLD, 12));
        uploadButtonSold.setSize(100, 20);
        uploadButtonSold.setLocation(310, 500);
        uploadButtonSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CMAFunctionality.FileHandling imageReader = new CMAFunctionality.FileHandling();
                String imageUploadMessage = imageReader.ImageUploader();

                JOptionPane.showMessageDialog(null, imageUploadMessage);
            }
        });
        cpHomesSold.add(uploadButtonSold);

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
                addressForSale.setText("");
                erfForSale.setText("");
                livingRoomForSale.setSelectedIndex(-1);
                bedroomForSale.setSelectedIndex(-1);
                bathroomForSale.setSelectedIndex(-1);
                garageForSale.setSelectedIndex(-1);
                poolForSale.setSelectedIndex(-1);
                flatForSale.setSelectedIndex(-1);
                domesticQuartForSale.setSelectedIndex(-1);
                daysOptionForSale.setSelectedIndex(-1);
                otherDetailForSale.setText("");
                listPriceForSale.setText("");
                soldPriceForSale.setText("");
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
                int livingRooms = Integer.parseInt(livingRoomSold.getSelectedItem().toString());
                int bedrooms = Integer.parseInt(bedroomSold.getSelectedItem().toString());
                int bathrooms = Integer.parseInt(bathroomSold.getSelectedItem().toString());
                int garages = Integer.parseInt(garageSold.getSelectedItem().toString());
                String pool = poolSold.getSelectedItem().toString();
                String flat = flatSold.getSelectedItem().toString();
                String domesticQuarters = domesticQuartSold.getSelectedItem().toString();
                String otherDetails = otherDetailSold.getText();
                int daysOnMarket = Integer.parseInt(daysSold.getSelectedItem().toString());
                double listPrice = Double.parseDouble(listPriceSold.getText());
                double soldPrice = Double.parseDouble(soldPriceSold.getText());


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

        //Expired/Old Listings Frame buttons ------------------------------------------------------------------------
        JButton resetButtonExpired = new JButton("Reset");
        resetButtonExpired.setFont(new Font("Arial", Font.BOLD, 12));
        resetButtonExpired.setSize(100, 20);
        resetButtonExpired.setLocation(50, 500);
        resetButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addressExpired.setText("");
                erfExpired.setText("");
                livingRoomExpired.setSelectedIndex(-1);
                bedroomExpired.setSelectedIndex(-1);
                bathroomExpired.setSelectedIndex(-1);
                garageExpired.setSelectedIndex(-1);
                poolExpired.setSelectedIndex(-1);
                flatExpired.setSelectedIndex(-1);
                domesticQuartExpired.setSelectedIndex(-1);
                daysExpired.setSelectedIndex(-1);
                otherDetailExpired.setText("");
                listPriceExpired.setText("");
                soldPriceExpired.setText("");
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
                int livingRooms = Integer.parseInt(livingRoomExpired.getSelectedItem().toString());
                int bedrooms = Integer.parseInt(bedroomExpired.getSelectedItem().toString());
                int bathrooms = Integer.parseInt(bathroomExpired.getSelectedItem().toString());
                int garages = Integer.parseInt(garageExpired.getSelectedItem().toString());
                String pool = poolExpired.getSelectedItem().toString();
                String flat = flatExpired.getSelectedItem().toString();
                String domesticQuarters = domesticQuartExpired.getSelectedItem().toString();
                String otherDetails = otherDetailExpired.getText();
                int daysOnMarket = Integer.parseInt(daysExpired.getSelectedItem().toString());
                double listPrice = Double.parseDouble(listPriceExpired.getText());
                double soldPrice = Double.parseDouble(soldPriceExpired.getText());

            }
        });
        cpExpiredListings.add(addButtonExpired);

        JButton uploadButtonExpired = new JButton("Upload Image");
        uploadButtonExpired.setFont(new Font("Arial", Font.BOLD, 12));
        uploadButtonExpired.setSize(100, 20);
        uploadButtonExpired.setLocation(310, 500);
        uploadButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CMAFunctionality.FileHandling imageReader = new CMAFunctionality.FileHandling();
                String imageUploadMessage = imageReader.ImageUploader();

                JOptionPane.showMessageDialog(null, imageUploadMessage);
            }
        });
        cpExpiredListings.add(uploadButtonExpired);

        JButton nextButtonExpired = new JButton("Next");
        nextButtonExpired.setFont(new Font("Arial", Font.BOLD, 12));
        nextButtonExpired.setSize(100, 20);
        nextButtonExpired.setLocation(440, 500);
        nextButtonExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




            }
        });
        cpExpiredListings.add(nextButtonExpired);

        //------------------------------------------------



        mainFrame.setVisible(true);

    }
}
