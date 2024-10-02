package com.Web.SAAS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ccsvreader {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\ganes\\eclipse-workspace\\webAutomation\\CSVupload\\addJQRproductlist1.csv";
        
        // Random 6-digit generator
        Random random = new Random();
        int random6Digit = 100000 + random.nextInt(900000); // Generates a 6-digit random number

        // Data to write in CSV
        String[] headers = {"category_id", "category_name", "name", "description", "product_code", "mrp", "size", "color"};
        String[] rowData = {"6", "Gents", "Firefly","sample description" , String.valueOf(random6Digit), "2999", "Samplesize", "Samplecolor"};

        // Writing the CSV
        FileWriter writer = new FileWriter(filePath);
            // Write headers
            for (int i = 0; i < headers.length; i++) {
                writer.append(headers[i]);
//                if (i < headers.length - 1) writer.append(",");
            }
            writer.append("\n");

            // Write data row
            for (int i = 0; i < rowData.length; i++) {
                writer.append(rowData[i]);
//                if (i < rowData.length - 1) writer.append(",");
            }
            writer.append("\n");

            System.out.println("CSV file created successfully at " + filePath); 
            writer.close();

    }
}


