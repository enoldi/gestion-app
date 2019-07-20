package com.ecommerce.gestion.controllers;

import com.ecommerce.gestion.domain.Order;
import com.ecommerce.gestion.dto.OrderProduitDTO;
import com.ecommerce.gestion.errors.BadRequestAlertException;
import com.ecommerce.gestion.service.OrderService;
import com.ecommerce.gestion.service.ProduitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(name = "/api")
public class OrderController {

    private final String ENTITY_NAME = "Panier Controller";


    private final OrderService orderService;

    /**
     * Constructor
     *
     * @param orderService
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * List of produit
     *
     * @return json of all produit
     */
    @GetMapping(name = "/orders")
    public ResponseEntity<Set<Order>> getOrders() {
        log.info("REST request to list all order : {}");
        return ResponseEntity.ok(orderService.getAllOrders());
    }


}
