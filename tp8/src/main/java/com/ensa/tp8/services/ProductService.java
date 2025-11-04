package com.ensa.tp8.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ensa.tp8.models.Product;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public ProductService() {
        // Initializing with some sample products
        products.add(new Product(1, "Produit A", "Description du Produit A"));
        products.add(new Product(2, "Produit B", "Description du Produit B"));
        products.add(new Product(3, "Produit C", "Description du Produit C"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return product;
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    public void deleteProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

}