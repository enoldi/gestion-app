package com.ecommerce.gestion.service;

import com.ecommerce.gestion.domain.Produit;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ProduitService {

    Set<Produit> getProduits();

    Produit findById(Long id);

    Produit findByCode(String code);

    Produit save(Produit produit);

    void deleteById(Long id);
}
