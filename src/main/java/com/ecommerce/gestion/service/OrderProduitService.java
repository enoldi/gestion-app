package com.ecommerce.gestion.service;

import com.ecommerce.gestion.domain.OrderProduit;
import com.ecommerce.gestion.domain.Produit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface OrderProduitService {

    OrderProduit saveOrUpdate(OrderProduit orderProduit);

    Set<OrderProduit> findOrderProduitInPanier();
}
