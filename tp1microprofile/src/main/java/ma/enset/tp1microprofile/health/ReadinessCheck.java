package ma.enset.tp1microprofile.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.enset.tp1microprofile.service.ClientService;
import ma.enset.tp1microprofile.service.ProductService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/**
 * Health check pour vérifier que l'application est prête à recevoir des
 * requêtes
 */
@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    @Inject
    private ProductService productService;

    @Inject
    private ClientService clientService;

    @Override
    public HealthCheckResponse call() {
        long productCount = productService.getProductCount();
        long clientCount = clientService.getClientCount();

        boolean isReady = productCount >= 0 && clientCount >= 0;

        return HealthCheckResponse
                .named("Readiness Check")
                .status(isReady)
                .withData("productCount", productCount)
                .withData("clientCount", clientCount)
                .withData("servicesInitialized", true)
                .withData("timestamp", System.currentTimeMillis())
                .build();
    }
}
