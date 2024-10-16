import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;


public class WordReportGenerator {

    public void wordReportGenerator () {
        XWPFDocument document = new XWPFDocument();

        try (FileOutputStream out = new FileOutputStream("PropertyReport.docx")) {

            // Add a title to the document
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Property Comparison Report");
            titleRun.setBold(true);
            titleRun.setFontSize(20);

            // Add a blank line
            XWPFParagraph blankLine = document.createParagraph();
            blankLine.createRun().addBreak();

            // Create a table for property comparison
            XWPFTable table = document.createTable();

            // Set table headers
            XWPFTableRow headerRow = table.getRow(0);
            headerRow.getCell(0).setText("Property Address");
            headerRow.addNewTableCell().setText("List Price");
            headerRow.addNewTableCell().setText("Days on Market");

            // Add sample data (you would typically fetch this from your database)
            String[][] propertyData = {
                    {"123 Main St", "1,200,000", "45"},
                    {"456 Oak St", "950,000", "60"},
                    {"789 Pine St", "2,300,000", "30"}
            };

            for (String[] property : propertyData) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(property[0]);
                row.getCell(1).setText(property[1]);
                row.getCell(2).setText(property[2]);
            }

            // Add a blank line after the table
            document.createParagraph().createRun().addBreak();

            // Add the average price section
            XWPFParagraph avgPricePara = document.createParagraph();
            XWPFRun avgPriceRun = avgPricePara.createRun();
            avgPriceRun.setText("Average List Price: R1,483,333"); // You would calculate this value dynamically
            avgPriceRun.setFontSize(16);
            avgPriceRun.setBold(true);

            // Save the document to disk
            document.write(out);

            System.out.println("Report generated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
