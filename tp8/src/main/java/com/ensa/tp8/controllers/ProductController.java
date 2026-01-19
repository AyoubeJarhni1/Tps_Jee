package com.ensa.tp8.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ensa.tp8.models.Product;
import com.ensa.tp8.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/Produits")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Récupère tous les produits")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des produits récupérée avec succès")
    })
    @GetMapping
    public List<Product> getAllProducts() {
        try {
            return productService.getAllProducts();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve products", e);
        }
    }

    @Operation(summary = "Récupère un produit par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produit récupéré avec succès"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        try {
            return productService.getProductById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not found", e);
        }
    }

    // --- POST /Produits : crée un nouveau produit
    @Operation(summary = "Crée un nouveau produit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produit créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        try {
            return productService.createProduct(product);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create product", e);
        }
    }

    // --- PUT /Produits/{id} : met à jour un produit existant
    @Operation(summary = "Met à jour un produit existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produit mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            return productService.updateProduct(id, product);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update product", e);
        }
    }

    // --- DELETE /Produits/{id} : supprime un produit
    @Operation(summary = "Supprime un produit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produit supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete product", e);
        }
    }
}
