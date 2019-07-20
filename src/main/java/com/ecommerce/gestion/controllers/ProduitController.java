package com.ecommerce.gestion.controllers;

import com.ecommerce.gestion.domain.Produit;
import com.ecommerce.gestion.errors.BadRequestAlertException;
import com.ecommerce.gestion.service.ProduitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(name = "/api")
public class ProduitController {

    private final String ENTITY_NAME = "Produit Controller";

    private final ProduitService produitService;

    /**
     * Constructor
     *
     * @param produitService
     */
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }


    /**
     * List of produit
     *
     * @return json of all produit
     */
    @GetMapping(path ="/produits")
    public ResponseEntity<Set<Produit>> getProduits() {
        return ResponseEntity.ok(produitService.getProduits());
    }

    /**
     * Save produit
     *
     * @param produit
     * @return Json produit save
     */
    @PostMapping(path ="/produits")
    public ResponseEntity<Produit> saveProduit(@RequestBody Produit produit) {
        return ResponseEntity.ok(produitService.save(produit));
    }

    /**
     * Find produit by id
     *
     * @param id
     * @return Json produit found
     */
    @GetMapping(path = "/produits/{produit-id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable(name="produit-id",required=true)Long id){
        Produit produit = produitService.findById(id);
        if (produit!=null)
            return ResponseEntity.ok(produit);
        return ResponseEntity.status(404).build();
    }

    /**
     * PUT  /produits : Updates an existing produit.
     *
     * @param produit the produit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated produit,
     * or with status 400 (Bad Request) if the produit is not valid,
     * or with status 500 (Internal Server Error) if the produit couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/produits")
    public ResponseEntity<Produit> updateDossier(@RequestBody Produit produit) throws URISyntaxException {
        log.debug("REST request to update Produit : {}", produit);
        if (produit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        final Produit result = produitService.save(produit);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * DELETE  /produits/:id : delete the "id" produit.
     *
     * @param id the id of the produit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/produits/{id}")
    public ResponseEntity.BodyBuilder deleteProduit(@PathVariable Long id) {
        log.debug("REST request to delete Produit : {}", id);
        produitService.deleteById(id);
        return ResponseEntity.ok();
    }
}
