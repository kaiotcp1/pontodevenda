package com.kaio.pdv.Entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item_sale")
public class ItemSale implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quanquity;

    public ItemSale() {
    }

    public ItemSale(Long id, Sale sale, Product product, int quanquity) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.quanquity = quanquity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuanquity() {
        return quanquity;
    }

    public void setQuanquity(int quanquity) {
        this.quanquity = quanquity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSale itemSale = (ItemSale) o;
        return Objects.equals(getId(), itemSale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
