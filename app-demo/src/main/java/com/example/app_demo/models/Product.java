package com.example.app_demo.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

    @Id
    private String id;
    private String name;
    private Double price;
    private Date creatAt;

    public Product(){}

    public Product(String name, Double price){
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Date getCreatAt() {
        return creatAt;
    }
    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public String toString() {
        return "Products [id=" + id + ", name=" + name + ", price=" + price + ", creatAt=" + creatAt + "]";
    }

    
}
