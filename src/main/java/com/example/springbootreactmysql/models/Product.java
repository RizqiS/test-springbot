package com.example.springbootreactmysql.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private Double price;

   public Long getId() {
      return id;
   }
   public String getName() {
      return name;
   }
   public Double getPrice() {
      return price;
   }
}
