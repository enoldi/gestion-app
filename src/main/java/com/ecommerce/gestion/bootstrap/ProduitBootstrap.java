package com.ecommerce.gestion.bootstrap;

import com.ecommerce.gestion.domain.Produit;
import com.ecommerce.gestion.repository.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ProduitBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProduitRepository produitRepository;

    public ProduitBootstrap(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        produitRepository.saveAll(getProduits());
        log.info("Loading bootstrap data");
    }

    private List<Produit> getProduits(){

        List<Produit> produits = new ArrayList<>(2);

        //Initialiser le produit 1 
        Produit produit = new Produit();
        produit.setCode("OOO45");
        produit.setLibelle("Banane");
        produit.setDescription("Un fruit de provenance d'Afrique centrale");
        produit.setQuantite(3800);
        produit.setMontant(230.00);
        produit.setImageUrl("https://www.femininbio.com/sites/femininbio.com/files/styles/article/public/styles/paysage/public/images/2013/01/banane.jpg?itok=B7GTuvnK");
        
        //ajouter le produit 1 au tableau
        produits.add(produit);

        //Initialiser le produit 2 
        Produit produit2 = new Produit();
        produit2.setCode("OOO46");
        produit2.setLibelle("Papaye");
        produit2.setDescription("Un fruit de provenance d'Afrique centrale");
        produit2.setQuantite(3800);
        produit2.setMontant(230.00);
        produit2.setImageUrl("https://www.coopathome.ch/img/produkte/880_880/RGB/4903240_001.jpg?_=1457790129614");

        //ajouter le produit 2 au tableau
        produits.add(produit2);

        return produits;
    }
}
