package com.ecommerce.gestion.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderProduitDTO implements Serializable {

    private Long id;

    private Integer quantite;

    private Long orderId;

    private Long produitId;
}
