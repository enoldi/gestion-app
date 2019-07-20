package com.ecommerce.gestion.service;

import com.ecommerce.gestion.domain.Order;
import com.ecommerce.gestion.domain.OrderProduit;
import com.ecommerce.gestion.domain.Produit;
import com.ecommerce.gestion.dto.OrderProduitDTO;
import com.ecommerce.gestion.repository.OrderProduitRepository;
import com.ecommerce.gestion.repository.OrderRepository;
import com.ecommerce.gestion.repository.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProduitRepository produitRepository;

    private final OrderProduitRepository orderProduitRepository;

    private final OrderProduitService orderProduitService;

    public OrderServiceImpl(OrderRepository orderRepository, ProduitRepository produitRepository,
                            OrderProduitRepository orderProduitRepository, OrderProduitService orderProduitService) {
        this.orderRepository = orderRepository;
        this.produitRepository = produitRepository;
        this.orderProduitRepository = orderProduitRepository;
        this.orderProduitService = orderProduitService;
    }


    @Override
    public Set<Order> getAllOrders() {
        Set<Order> orders = new HashSet<>();
        log.info("I am a service to give all orders");
        this.orderRepository.findAll().iterator().forEachRemaining(orders::add);
        return orders;
    }

    @Override
    public Order getPanier() {
        log.info("I am a service to give orders in panier");
        Optional<Order> optionalOrder = orderRepository.findOrderByStatus("Encours");
        if (!optionalOrder.isPresent()){
            throw new RuntimeException("Produit not Found!");
        }
        return optionalOrder.get();
    }

    @Override
    public Order saveOrUpdate(Order order) {
        log.info("I am a service to update order");
        return this.orderRepository.save(order);
    }

    @Override
    public OrderProduit addPrduit(OrderProduitDTO orderProduitDTO){

        Optional<Produit> optional = produitRepository.findById(orderProduitDTO.getProduitId());

        System.out.println("l'entite a enregistre est:" + optional.get());

        String statut = "Encours";

        //Initialisation OrderSaved
        Order orderSaved = null;

        //Initialisation OrderSaved
        final OrderProduit[] orderProduitSaved = {null};

        Optional<Order> optionalOrder = orderRepository.findOrderByStatus(statut);

        if (optional.isPresent()) {

            if (optionalOrder.isPresent()){

                Optional<OrderProduit> produitInCart = getProductById(optionalOrder.get().getOrderProduits(), orderProduitDTO.getId());

                if(produitInCart.isPresent()) {
                    produitInCart.get().setQuantite(orderProduitDTO.getQuantite());
                    optionalOrder.get().getOrderProduits().stream().forEach(orderProduit -> {
                        if (orderProduit.getId().equals(orderProduitDTO.getId())){
                            orderProduit.setQuantite(orderProduitDTO.getQuantite());
                            orderProduit.setProduit(optional.get());

                            //Set OrderId in orderProduit
                            orderProduit.setOrder(optionalOrder.get());

                            //Save orderProduit
                            orderProduitSaved[0] = orderProduitService.saveOrUpdate(orderProduit);
                        }
                    });

                } else {
                    OrderProduit orderProduit = new OrderProduit();
                    orderProduit.setQuantite(orderProduitDTO.getQuantite());
                    orderProduit.setProduit(produitRepository.findById(orderProduitDTO.getProduitId()).get());

                    //Add orderProduit in order
                    optionalOrder.get().getOrderProduits().add(orderProduit);

                    //Set OrderId in orderProduit
                    orderProduit.setOrder(optionalOrder.get());

                    //Save orderProduit
                    orderProduitSaved[0] = orderProduitService.saveOrUpdate(orderProduit);
                }

                //Save order
                orderSaved = orderRepository.save(optionalOrder.get());

            }else {
                //We create new order
                Order order = new Order();
                order.setStatus(statut);
                order.setDateCreated(LocalDate.now());

                OrderProduit orderProduit = new OrderProduit();
                orderProduit.setQuantite(orderProduitDTO.getQuantite());
                orderProduit.setProduit(optional.get());

                //Save orderProduit
                OrderProduit orderProdSaved = orderProduitService.saveOrUpdate(orderProduit);


                order.getOrderProduits().add(orderProdSaved);

                //Save order
                orderSaved = orderRepository.save(order);

                //Set order in orderProduit
                orderProduit.setOrder(orderSaved);

                //Update
                orderProduitSaved[0] = orderProduitService.saveOrUpdate(orderProduit);

            }
        }

        return orderProduitSaved[0];
    }

    @Override
    public Order removeOrderPrduit(OrderProduitDTO orderProduitDTO){

        Optional<OrderProduit> optional = orderProduitRepository.findById(orderProduitDTO.getId());

        String statut = "Encours";
        Optional<Order> optionalOrder = orderRepository.findOrderByStatus(statut);

        if (optional.isPresent() & optionalOrder.isPresent()) {
            Optional<OrderProduit> produitInCart = getProductById(optionalOrder.get().getOrderProduits(), orderProduitDTO.getId());

            
            if(produitInCart.isPresent()) {
                optionalOrder.get().getOrderProduits().remove(optional.get());
                optional.get().setOrder(null);
                orderProduitRepository.delete(optional.get());
            }
        }

        return saveOrUpdate(optionalOrder.get());
    }

    //find produit
    private Optional<OrderProduit> getProductById(List<OrderProduit> produits, Long id) {
        return produits.stream()
                .filter(produit -> produit.getId().equals(id))
                .findFirst();
    }

}
