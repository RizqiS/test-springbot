package com.example.springbootreactmysql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootreactmysql.repository.ProductRepository;
import com.example.springbootreactmysql.models.Product;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
   private final ProductRepository productRepository;

   @Autowired
   public ProductService(ProductRepository productRepo){ 
      this.productRepository = productRepo;
   }

   public List<Product> getAllProduct() {
      return productRepository.findAll();
   }

   public Product saveProduct(Product product) {
      return productRepository.save(product);
   }

   public Product getProductById(Long id) {
      return productRepository.findById(id).orElse(null);
   }

   public void deleteProduct(Long id) {
      Objects.requireNonNull(id, "id cannot be null");
      productRepository.deleteById(id);
      
   }
}
