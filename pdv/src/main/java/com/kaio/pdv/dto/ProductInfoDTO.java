package com.kaio.pdv.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductInfoDTO {

    private long id;
    private String description;
    private int quantity;
    private BigDecimal price;

    public ProductInfoDTO() {
    }

    public ProductInfoDTO(String description, int quantity, long id, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.price = price;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
