package com.ecommerce.gestion.controllers;

import com.ecommerce.gestion.domain.Order;
import com.ecommerce.gestion.domain.OrderProduit;
import com.ecommerce.gestion.dto.OrderProduitDTO;
import com.ecommerce.gestion.service.OrderProduitService;
import com.ecommerce.gestion.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api")
public class OrderProduitController {

    private final String ENTITY_NAME = "orderProduit Controller";

    private final OrderService orderService;

    private final OrderProduitService orderProduitService;

    public OrderProduitController(OrderService orderService, OrderProduitService orderProduitService) {
        this.orderService = orderService;
        this.orderProduitService = orderProduitService;
    }

    /**
     * Post  /order-produit/add-items : Add produit to a cart.
     *
     * @param orderProduitDTO the orderProduit to add
     * @return the ResponseEntity with status 200 (OK) and with body the updated order,
     * or with status 400 (Bad Request) if the order is not valid,
     * or with status 500 (Internal Server Error) if the order couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(path = "/order-produit/add-items")
    public ResponseEntity<OrderProduit> addItemToCart(@RequestBody OrderProduitDTO orderProduitDTO) throws URISyntaxException {
        log.debug("REST request to add orderProduit : {}", orderProduitDTO.getId());
        return ResponseEntity.ok(orderService.addPrduit(orderProduitDTO));
    }


    /**
     * remove product to cart
     *
     * @param orderProduitDTO
     * @return Json order save
     */
    @DeleteMapping(path ="/order-produit/removes-items")
    public ResponseEntity<Order> removeProduitToCart(@RequestBody OrderProduitDTO orderProduitDTO) {
        return ResponseEntity.ok(orderService.removeOrderPrduit(orderProduitDTO));
    }

    /**
     * get panier of produit
     *
     * @return json of all produit
     */
    @GetMapping("/order-produit/paniers")
    public ResponseEntity<Set<OrderProduit>> getOrderPanier() {
        log.info("REST request to get panier : {}");
        return ResponseEntity.ok(orderProduitService.findOrderProduitInPanier());
    }
}
