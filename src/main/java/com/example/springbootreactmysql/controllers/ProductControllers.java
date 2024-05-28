package com.example.springbootreactmysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.springbootreactmysql.services.ProductService;
import com.example.springbootreactmysql.models.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequestMapping("/product")
public class ProductControllers {
   private final ProductService productService;

   @Autowired
   public ProductControllers(ProductService service) {
      this.productService = service;
   }

   @GetMapping
   public List<Product> getAllProduct() {
      List<Product> products = productService.getAllProduct();
      return products;
   }

   @PostMapping
   public Product addProduct(@RequestBody Product product, BindingResult bindingResult) {
      if(bindingResult.hasErrors()) {
         throw new IllegalArgumentException("product is not valid: " + bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", ")));
      }
      
      if(product == null || product.getName().isEmpty() || product.getPrice() <= 0) {
         throw new IllegalArgumentException("invalid passed your input, please check your input again");
      }

      Product products = productService.saveProduct(product);
      return products;
   }

   @GetMapping("/{id}")
   public Product getProductById(@PathVariable Long id) {
      if(id == null) {
         throw new IllegalArgumentException("Product ID cannot be null");
      }
      return productService.getProductById(id);
   }

   @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
   public String deleteProduct(@PathVariable Long id) {
      Product products = productService.getProductById(id);
      if(products == null) {
         return "Product with id " + id + " not found";
      }

      productService.deleteProduct(id);
      return "succes delete product";
   }
}
