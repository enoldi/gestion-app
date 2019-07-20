package com.ecommerce.gestion.service;

import com.ecommerce.gestion.domain.Order;
import com.ecommerce.gestion.domain.OrderProduit;
import com.ecommerce.gestion.repository.OrderProduitRepository;
import com.ecommerce.gestion.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class OrderProduitServiceImpl implements OrderProduitService {

    private  final OrderProduitRepository orderProduitRepository;

    private final OrderRepository orderRepository;

    public OrderProduitServiceImpl(OrderProduitRepository orderProduitRepository, OrderRepository orderRepository) {
        this.orderProduitRepository = orderProduitRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderProduit saveOrUpdate(OrderProduit orderProduit) {
        return this.orderProduitRepository.save(orderProduit);
    }

    @Override
    public Set<OrderProduit> findOrderProduitInPanier(){

        String statut = "Encours";

        Set<OrderProduit> orderList = new HashSet<>();

        Optional<Order> optionalOrder = orderRepository.findOrderByStatus(statut);
        orderProduitRepository.findOrderProduitByOrder(optionalOrder.get()).iterator().forEachRemaining(orderList::add);

        return orderList;
    }


}
