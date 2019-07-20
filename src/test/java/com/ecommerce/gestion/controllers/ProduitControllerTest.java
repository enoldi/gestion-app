package com.ecommerce.gestion.controllers;

import com.ecommerce.gestion.domain.Produit;
import com.ecommerce.gestion.repository.ProduitRepository;
import com.ecommerce.gestion.service.ProduitService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class ProduitControllerTest {

    private MockMvc mvc;

    @Mock
    ProduitService produitService;

    @MockBean
    ProduitController produitController;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        produitController = new ProduitController(produitService);

        mvc = MockMvcBuilders.standaloneSetup(produitController).build();
    }

    @Test
    public void shouldFindAllProduits() throws Exception {

        Produit produit =new Produit();
        produit.setId(1L);
        produit.setCode("CCCCCCCC");
        produit.setLibelle("AAAAAAAAAAAAAAA");
        produit.setDescription("AAAAAAAAAAAAAAA");
        produit.setQuantite(23);
        produit.setMontant(23D);

        Produit pomme =new Produit();
        pomme.setId(2L);
        pomme.setCode("DDDDDDD");
        pomme.setLibelle("AAAAAAAAAAAAAAA");
        pomme.setDescription("AAAAAAAAAAAAAAA");
        pomme.setQuantite(23);
        pomme.setMontant(23D);

        Set<Produit> produits = Stream.of(produit,pomme).collect(Collectors.toSet());


        given(this.produitService.getProduits())
                .willReturn(produits);

        this.mvc.perform(get("/api/produits")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", hasSize(2)))
                .andExpect(jsonPath("$.[0].code", is("CCCCCCCC")));

    }
}