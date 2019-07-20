package com.ecommerce.gestion.repository;

import com.ecommerce.gestion.domain.Order;
import com.ecommerce.gestion.domain.OrderProduit;
import com.ecommerce.gestion.domain.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProduitRepository extends JpaRepository<OrderProduit, Long> {

    Optional<OrderProduit> findOrderProduitByProduit(Produit produit);

    List<OrderProduit> findOrderProduitByOrder(Order order);
}
