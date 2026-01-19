package ma.enset.tp1microprofile;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

/**
 * Configuration de l'application JAX-RS avec MicroProfile
 */
@ApplicationPath("/api")
@OpenAPIDefinition(info = @Info(title = "TP1 MicroProfile API", version = "1.0.0", description = "API REST pour le TP MicroProfile - Gestion de produits et clients", contact = @Contact(name = "ENSET", email = "contact@enset.ma")), servers = {
        @Server(url = "http://localhost:9080/tp1microprofile", description = "Serveur de développement")
})
public class RestApplication extends Application {
}
