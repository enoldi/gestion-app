package com.ecommerce.gestion.dto;

import com.ecommerce.gestion.domain.OrderProduit;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private LocalDate dateCreated;

    private String status;

    private List<OrderProduit> orderProduits = new ArrayList<>();
}
