package ma.enset.tp1microprofile.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Configuration de l'application avec MicroProfile Config
 */
@ApplicationScoped
public class ApplicationConfig {

    @ConfigProperty(name = "app.name", defaultValue = "TP1 MicroProfile")
    private String appName;

    @ConfigProperty(name = "app.version", defaultValue = "1.0.0")
    private String appVersion;

    @ConfigProperty(name = "app.description", defaultValue = "Application MicroProfile pour la gestion de produits et clients")
    private String appDescription;

    @ConfigProperty(name = "app.max.products", defaultValue = "1000")
    private Integer maxProducts;

    @ConfigProperty(name = "app.max.clients", defaultValue = "500")
    private Integer maxClients;

    @ConfigProperty(name = "app.enable.metrics", defaultValue = "true")
    private Boolean enableMetrics;

    @ConfigProperty(name = "app.enable.health", defaultValue = "true")
    private Boolean enableHealth;

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public Integer getMaxProducts() {
        return maxProducts;
    }

    public Integer getMaxClients() {
        return maxClients;
    }

    public Boolean getEnableMetrics() {
        return enableMetrics;
    }

    public Boolean getEnableHealth() {
        return enableHealth;
    }
}
