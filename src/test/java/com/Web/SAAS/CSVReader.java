package com.Web.SAAS;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Test;

import com.opencsv.CSVWriter;

public class CSVReader {
	
@Test
    public void reader() throws IOException {
        // Define the file path
        String filePath = "C:\\Users\\ganes\\eclipse-workspace\\webAutomation\\CSVupload\\addJQRproductlist1.csv";
        
        // Define the header (column names)
        String[] header = {"category_id", "category_name", "name", "description", "product_code", "mrp", "size", "color"};
        
        Random random = new Random();
        int random6Digit = 100000 + random.nextInt(900000);
        String str = Integer.toString(random6Digit);
      
        System.out.println(random6Digit);
       
        String[] rowData = {"6", "Gents", "Firefly", str, "8787871", "2999", "Samplesize", "Samplecolor"};
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
          
            writer.writeNext(header);
            
            // Write the second row with random data in the 4th column 
            writer.writeNext(rowData);
            
            System.out.println("CSV file created successfully.");
 
    }

    
}

