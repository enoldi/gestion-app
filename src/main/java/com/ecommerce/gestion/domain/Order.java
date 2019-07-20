package com.ecommerce.gestion.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "panier")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreated;

    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderProduit> orderProduits = new ArrayList<>();

    public Order(){

    }

    public Order(LocalDate dateCreated, @Valid List<OrderProduit> orderProduits) {
        this.dateCreated = dateCreated;
        this.orderProduits = orderProduits;
    }


    @Transient
    public Double getTotalPrice() {
        return getOrderProduits().stream().mapToDouble(orderProduit->orderProduit.getTotalPrice()).sum();
    }
}
