package ma.enset.tp1microprofile.health;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Startup;

/**
 * Health check pour vérifier que l'application a démarré correctement
 */
@Startup
@ApplicationScoped
public class StartupCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        // Vérification que l'application a bien démarré
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long freeMemory = runtime.freeMemory();

        return HealthCheckResponse
                .named("Startup Check")
                .status(true)
                .withData("maxMemory", maxMemory / (1024 * 1024) + " MB")
                .withData("freeMemory", freeMemory / (1024 * 1024) + " MB")
                .withData("startupCompleted", true)
                .withData("timestamp", System.currentTimeMillis())
                .build();
    }
}
