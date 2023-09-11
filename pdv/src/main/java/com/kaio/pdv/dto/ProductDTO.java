package com.kaio.pdv.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ProductDTO {

    private long productId;
    private int quantity;

    public ProductDTO() {
    }

    public ProductDTO(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
