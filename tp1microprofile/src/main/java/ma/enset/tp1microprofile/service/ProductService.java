package ma.enset.tp1microprofile.service;

import jakarta.enterprise.context.ApplicationScoped;
import ma.enset.tp1microprofile.model.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Service pour la gestion des produits
 */
@ApplicationScoped
public class ProductService {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public ProductService() {
        // Initialisation avec quelques produits
        initializeData();
    }

    private void initializeData() {
        createProduct(new Product(null, "Laptop Dell XPS 13", "Ordinateur portable haute performance", 12999.99, 10,
                "Informatique"));
        createProduct(
                new Product(null, "iPhone 15 Pro", "Smartphone Apple dernière génération", 13999.99, 15, "Téléphonie"));
        createProduct(new Product(null, "Samsung Galaxy S24", "Smartphone Samsung avec IA", 9999.99, 20, "Téléphonie"));
        createProduct(new Product(null, "MacBook Pro M3", "Ordinateur portable Apple avec puce M3", 24999.99, 5,
                "Informatique"));
        createProduct(new Product(null, "AirPods Pro", "Écouteurs sans fil Apple", 2999.99, 30, "Audio"));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> getProductsByCategory(String category) {
        return products.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Product createProduct(Product product) {
        Long id = idGenerator.incrementAndGet();
        product.setId(id);
        products.put(id, product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product product) {
        if (!products.containsKey(id)) {
            return Optional.empty();
        }
        product.setId(id);
        products.put(id, product);
        return Optional.of(product);
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }

    public long getProductCount() {
        return products.size();
    }

    public List<Product> searchProducts(String keyword) {
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
