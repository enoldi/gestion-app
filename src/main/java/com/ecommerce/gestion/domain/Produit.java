package com.ecommerce.gestion.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Table(name = "produit")
@Data
@Entity
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Size(max = 10)
    private String code;

    @Size(max = 50)
    private String libelle;

    private String description;

    private Double montant;

    private Integer quantite;

    private String imageUrl;
}
