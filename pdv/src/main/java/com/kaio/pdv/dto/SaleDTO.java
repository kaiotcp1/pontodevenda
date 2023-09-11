package com.kaio.pdv.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SaleDTO{

    private long userid;
    List<ProductDTO> items;

    public SaleDTO() {
    }

    public SaleDTO(long userid, List<ProductDTO> items) {
        this.userid = userid;
        this.items = items;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public List<ProductDTO> getItems() {
        return items;
    }

    public void setItems(List<ProductDTO> items) {
        this.items = items;
    }

}
