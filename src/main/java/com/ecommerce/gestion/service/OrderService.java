package com.ecommerce.gestion.service;

import com.ecommerce.gestion.domain.Order;
import com.ecommerce.gestion.domain.OrderProduit;
import com.ecommerce.gestion.dto.OrderProduitDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface OrderService {
    Set<Order> getAllOrders();

    Order getPanier();

    Order saveOrUpdate(Order order);

    OrderProduit addPrduit(OrderProduitDTO orderProduitDTO);

    Order removeOrderPrduit(OrderProduitDTO orderProduitDTO);
}
