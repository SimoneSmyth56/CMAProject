import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

import java.io.*;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;


public class WordReportGenerator {

    CMAGui gui = new CMAGui();
    CMAFunctionality cmaFunctionality = new CMAFunctionality();
    DatabaseFunctions databaseFunctions = new DatabaseFunctions();


    public String wordReportGenerator() {
        XWPFDocument document = new XWPFDocument();
        String filePath = "PropertyReport.docx";

        // Set custom page margins
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();

        // Units are in twentieths of a point (2.54 cm = 1440 twentieths of a point)
        pageMar.setLeft(750);
        pageMar.setRight(850);
        pageMar.setTop(600);
        pageMar.setBottom(850);


        //Setting document as landscape
        sectPr.addNewPgSz().setOrient(STPageOrientation.LANDSCAPE);
        sectPr.addNewPgSz().setW(BigInteger.valueOf(16840));
        sectPr.addNewPgSz().setH(BigInteger.valueOf(11900));

        // COVER PAGE ______________________________________________________
        XWPFParagraph coverParagraph = document.createParagraph();

        // Set vertical alignment by adding spacing before and after the image
        coverParagraph.setSpacingBefore(300); // Adjust value for vertical centering
        coverParagraph.setSpacingAfter(300);  // Adjust value for vertical centering

        coverParagraph.setAlignment(ParagraphAlignment.CENTER);


        try (OutputStream out = new FileOutputStream(filePath)) {

            XWPFRun run = coverParagraph.createRun();

            File image = new File("images/CMACover.png");

            FileInputStream imageData = new FileInputStream(image);

            //Retrieving the image file name and image type
            int imageType = XWPFDocument.PICTURE_TYPE_JPEG;
            String imageFileName = image.getName();

            int width = 750;
            int height = 550;

            run.addPicture(imageData, imageType, imageFileName,
                    Units.toEMU(width),
                    Units.toEMU(height));

            coverParagraph.setPageBreak(true);

            //__________________________________________________________

            // INTRO PAGE ____________________________________________________

            XWPFParagraph page1Paragraph = document.createParagraph();

            page1Paragraph.setSpacingBefore(300);
            page1Paragraph.setSpacingAfter(300);

            page1Paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun runPage1 = page1Paragraph.createRun();

            File imageCMADiagram = new File("images/CMADiagram.png");

            FileInputStream imageDataCMADiagram = new FileInputStream(imageCMADiagram);

            //Retrieving the image file name and image type
            int imageTypeCMADiagram = XWPFDocument.PICTURE_TYPE_JPEG;
            String CMADiagramFileName = image.getName();

            int CMADiagramwidth = 750;
            int CMADiagramheight = 450;

            runPage1.addPicture(imageDataCMADiagram, imageTypeCMADiagram, CMADiagramFileName,
                    Units.toEMU(CMADiagramwidth),
                    Units.toEMU(CMADiagramheight));

            coverParagraph.setPageBreak(true);

            // TABLE COMPARISON PAGE _____________________________________

            XWPFParagraph page2Paragraph = document.createParagraph();

            page2Paragraph.setAlignment(ParagraphAlignment.CENTER);


            XWPFRun titleRun = page2Paragraph.createRun();
            titleRun.setText("Comparable Listings");
            titleRun.setBold(true);
            titleRun.setFontSize(20);
            titleRun.setColor("288081"); // Set font color to R40G128B129 in hexadecimal (288081). RGB: #288081 is equivalent to R40G128B129 );
            titleRun.addBreak();

            try {
                XWPFParagraph forSaleHeading = document.createParagraph();
                forSaleHeading.setAlignment(ParagraphAlignment.CENTER);

                XWPFRun forSaleHeadingRun = forSaleHeading.createRun();
                forSaleHeadingRun.setText("Similar Homes Currently For Sale");
                forSaleHeadingRun.setBold(true);
                forSaleHeadingRun.setFontSize(14);
                forSaleHeadingRun.setColor("288081");
                forSaleHeadingRun.addBreak();

                XWPFRun forSaleSubHeadingRun = forSaleHeading.createRun();
                forSaleSubHeadingRun.setText("This tells us what we are competing against. Buyers will compare your home with these homes");
                forSaleSubHeadingRun.setBold(true);
                forSaleSubHeadingRun.setFontSize(10);
                forSaleSubHeadingRun.setColor("808080");
                forSaleSubHeadingRun.addBreak();

                List<DatabaseFunctions.Property> propertiesForSale = DatabaseFunctions.fetchPropertiesForSale();
                createTable(document, propertiesForSale);

//                List<DatabaseFunctions.Property> propertiesSold = DatabaseFunctions.fetchPropertiesSold();
//                createTable(document, propertiesSold, "Similar homes recently Sold");
//
//                List<DatabaseFunctions.Property> propertiesExpired = DatabaseFunctions.fetchPropertiesExpired();
//                createTable(document, propertiesExpired, "Expired / Old Listings");

                document.createParagraph().createRun().addBreak();

                averagePriceSection(document);

                XWPFParagraph infoHeadingParagraph = document.createParagraph();
                XWPFRun infoHeadingRun = infoHeadingParagraph.createRun();
                infoHeadingRun.setText("Challenges with Overpricing:");
                infoHeadingRun.setBold(true);
                infoHeadingRun.setFontSize(14);
                infoHeadingRun.setColor("808080");
                infoHeadingRun.addCarriageReturn();

                XWPFParagraph infoParagraph = document.createParagraph();
                XWPFRun infoRun = infoParagraph.createRun();

                infoRun.setText("- It becomes difficult to get real estate agents to actively market the property.");
                infoRun.addCarriageReturn();
                infoRun.setText("- Potential buyers are hesitant to make offers.");
                infoRun.addCarriageReturn();
                infoRun.setText("- Attracting quality buyers becomes a challenge.");
                infoRun.addCarriageReturn();
                infoRun.setText("- Securing financing for the sale is more difficult.");
                infoRun.addCarriageReturn();
                infoRun.setText("- Ultimately, the property may not sell.");
                infoRun.addCarriageReturn();
                infoRun.addCarriageReturn();

                XWPFParagraph infoHeading2Paragraph = document.createParagraph();
                XWPFRun infoHeading2Run = infoHeading2Paragraph.createRun();
                infoHeading2Run.setText("Selling guidelines to determine pricing:");
                infoHeading2Run.setBold(true);
                infoHeading2Run.setFontSize(14);
                infoHeading2Run.setColor("808080");
                infoHeading2Run.addCarriageReturn();

                XWPFParagraph info2Paragraph = document.createParagraph();
                XWPFRun info2Run = info2Paragraph.createRun();

                info2Run.setText("- Consider the location of the property.");
                info2Run.addCarriageReturn();
                info2Run.setText("- Understand the reasons for selling.");
                info2Run.addCarriageReturn();
                info2Run.setText("- Highlight any unique or exciting features of the property.");
                info2Run.addCarriageReturn();
                info2Run.setText("- Take into account the urgency of the sale.");
                info2Run.addCarriageReturn();
                info2Run.setText("- Explore any special financing options available.");

                infoRun.setFontSize(12);
                info2Run.setFontSize(12);



                document.write(out);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (InvalidFormatException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return "Report has been generated";
    }

    private void averagePriceSection(XWPFDocument document) {

        XWPFParagraph avgPricePara = document.createParagraph();
        XWPFRun avgPriceRun = avgPricePara.createRun();
        avgPriceRun.setText("Average List Price: R" + databaseFunctions.avgEstimateValue());
        avgPriceRun.setFontSize(16);
        avgPriceRun.setBold(true);

    }

    private void createTable(XWPFDocument document, List<DatabaseFunctions.Property> properties) {

        XWPFTable table = document.createTable(1, 12);
        insertTableHeader(table);

        table.setWidth("100%");


        for (DatabaseFunctions.Property property : properties) {
            XWPFTableRow row = table.createRow();
            insertPropertyData(row, property);
        }
    }

    // Insert table headers
    private void insertTableHeader(XWPFTable table) {
        XWPFTableRow headerRow = table.getRow(0);
        headerRow.getCell(0).setText("Address");
        headerRow.getCell(1).setText("ERF Size");
        headerRow.getCell(2).setText("Living Room");
        headerRow.getCell(3).setText("Bedrooms");
        headerRow.getCell(4).setText("Bathrooms");
        headerRow.getCell(5).setText("Garage");
        headerRow.getCell(6).setText("Pool");
        headerRow.getCell(7).setText("Flat");
        headerRow.getCell(8).setText("Domestic Quarters");
        headerRow.getCell(9).setText("Other Details");
        headerRow.getCell(10).setText("Days on the market");
        headerRow.getCell(11).setText("List Price");
    }

    // Insert property data into a row
    private void insertPropertyData(XWPFTableRow row, DatabaseFunctions.Property property) {
        row.getCell(0).setText(property.address);
        row.getCell(1).setText(String.valueOf(property.erf_size));
        row.getCell(2).setText(String.valueOf(property.living_room));
        row.getCell(3).setText(String.valueOf(property.bedroom));
        row.getCell(4).setText(String.valueOf(property.bathroom));
        row.getCell(5).setText(String.valueOf(property.garage));
        row.getCell(6).setText(property.pool ? "Yes" : "No");
        row.getCell(7).setText(property.flat ? "Yes" : "No");
        row.getCell(8).setText(property.domestic_quar ? "Yes" : "No");
        row.getCell(9).setText(property.other_detail);
        row.getCell(10).setText(String.valueOf(property.days_on_market));
        row.getCell(11).setText(String.format("R %.2f", property.list_price));
    }

}
