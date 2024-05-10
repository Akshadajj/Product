package service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import model.Product;

@Service
public class ProductService {

 // products.csv
 private static final String CSV_FILE_PATH = "products.csv";

 public void insertProduct(Product product) {
     try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
         // Append product data to CSV file
         writer.println(product.getProductId() + "," + product.getProductName() + "," + product.getProductDescription() + "," + product.getFlag());
     } catch (IOException e) {
         e.printStackTrace();
         // Handle exception (e.g., log error or throw custom exception)
     }
 }
 public List<Product> readCSV() {
     List<Product> products = new ArrayList<>();
     try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
         String[] record;
         while ((record = reader.readNext()) != null) {
             Product product = new Product();
             product.setProductId(Integer.parseInt(record[0]));
             product.setProductName(record[1]);
             product.setProductDescription(record[2]);
             product.setFlag(Integer.parseInt(record[3]));
             if (product.getFlag() == 1) {
                 products.add(product);
             }
         }
     } catch (IOException | CsvValidationException e) {
         e.printStackTrace();
     }
     return products;
 }
 
}
