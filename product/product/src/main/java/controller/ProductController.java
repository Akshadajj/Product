package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import model.Product;
import service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

 @Autowired
 private ProductService productService;

 @PostMapping("/insert")
 public ResponseEntity<String> insertProduct(@RequestBody Product product) {
     // Set flag to 1 for each field
     product.setFlag(1);
     productService.insertProduct(product);
     return ResponseEntity.ok("Product inserted successfully");
 }
 @GetMapping("/display")
 public ModelAndView displayProducts() {
     List<Product> products = productService.readCSV();
     ModelAndView modelAndView = new ModelAndView("products");
     modelAndView.addObject("products", products);
     return modelAndView;

}
}
