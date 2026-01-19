package ma.enset.tp1microprofile.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.enset.tp1microprofile.config.ApplicationConfig;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Ressource pour récupérer la configuration de l'application
 */
@Path("/config")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Configuration", description = "Informations de configuration de l'application")
public class ConfigResource {

    @Inject
    private ApplicationConfig config;

    @GET
    @Operation(summary = "Récupérer la configuration", description = "Retourne les informations de configuration de l'application")
    @APIResponse(responseCode = "200", description = "Configuration récupérée avec succès")
    public Response getConfig() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("name", config.getAppName());
        configMap.put("version", config.getAppVersion());
        configMap.put("description", config.getAppDescription());
        configMap.put("maxProducts", config.getMaxProducts());
        configMap.put("maxClients", config.getMaxClients());
        configMap.put("metricsEnabled", config.getEnableMetrics());
        configMap.put("healthEnabled", config.getEnableHealth());

        return Response.ok(configMap).build();
    }
}
