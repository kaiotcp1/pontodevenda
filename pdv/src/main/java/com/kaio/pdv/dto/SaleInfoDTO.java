package com.kaio.pdv.dto;

import java.math.BigDecimal;
import java.util.List;

public class SaleInfoDTO {

    private String user;
    private String date;
    private BigDecimal total;
    private List<ProductInfoDTO> products;

    public SaleInfoDTO() {
    }

    public SaleInfoDTO(String user, String date,BigDecimal total, List<ProductInfoDTO> products) {
        this.user = user;
        this.date = date;
        this.total = total;
        this.products = products;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ProductInfoDTO> getProducts() {
        return products;
    }

    public void setProduts(List<ProductInfoDTO> products) {
        this.products = products;
    }
}
