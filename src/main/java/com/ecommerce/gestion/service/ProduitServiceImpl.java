package com.ecommerce.gestion.service;

import com.ecommerce.gestion.domain.Produit;
import com.ecommerce.gestion.repository.OrderProduitRepository;
import com.ecommerce.gestion.repository.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    private final OrderProduitRepository orderProduitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository, OrderProduitRepository orderProduitRepository) {
        this.produitRepository = produitRepository;
        this.orderProduitRepository = orderProduitRepository;
    }

    @Override
    public Set<Produit> getProduits(){
        log.info("I am a service to give all produit");

        Set<Produit> produits = new HashSet<>();

        produitRepository.findAll().iterator().forEachRemaining(produits::add);

        return produits;
    }

    @Override
    public Produit findById(Long id){
        Optional<Produit> optionalProduit = produitRepository.findById(id);

        if (!optionalProduit.isPresent()){
            throw new RuntimeException("Produit not Found!");
        }

        return optionalProduit.get();
    }

    @Override
    public Produit findByCode(String code){
        Optional<Produit> optionalProduit = produitRepository.findProduitByCode(code);

        if (!optionalProduit.isPresent()){
            throw new RuntimeException("Produit not Found!");
        }

        return optionalProduit.get();
    }


    @Override
    public Produit save(Produit produit){
        return produitRepository.save(produit);
    }

    @Override
    public void deleteById(Long id){
        produitRepository.deleteById(id);
    }
}
