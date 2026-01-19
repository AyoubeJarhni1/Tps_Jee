package ma.enset.tp1microprofile.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.enset.tp1microprofile.service.ProductService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/**
 * Health check pour vérifier que l'application est vivante
 */
@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

    @Inject
    private ProductService productService;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("Liveness Check")
                .status(true)
                .withData("application", "TP1 MicroProfile")
                .withData("status", "UP")
                .withData("timestamp", System.currentTimeMillis())
                .build();
    }
}
