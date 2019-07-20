package com.ecommerce.gestion.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "order_produit")
public class OrderProduit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantite;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Produit produit;

    public OrderProduit(){

    }

    public OrderProduit(Produit produit, Integer quantite) {
        this.setProduit(produit);
        this.quantite = quantite;
    }

    public OrderProduit(Order order, Produit produit, Integer quantite) {
        this.setOrder(order);
        this.setProduit(produit);
        this.quantite = quantite;
    }

    @Transient
    public Double getTotalPrice() {
        return getProduit().getMontant() * getQuantite();
    }


}
