package ma.enset.tp1microprofile.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.enset.tp1microprofile.model.Client;
import ma.enset.tp1microprofile.service.ClientService;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

/**
 * Ressource REST pour la gestion des clients
 */
@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Clients", description = "Opérations CRUD sur les clients")
public class ClientResource {

    @Inject
    private ClientService clientService;

    @GET
    @Operation(summary = "Récupérer tous les clients", description = "Retourne la liste complète des clients")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Liste des clients récupérée avec succès", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Client.class)))
    })
    @Counted(name = "getAllClientsCount", description = "Nombre d'appels à getAllClients")
    @Timed(name = "getAllClientsTimer", description = "Temps d'exécution de getAllClients")
    public Response getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return Response.ok(clients).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Récupérer un client par ID", description = "Retourne un client spécifique")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Client trouvé", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Client.class))),
            @APIResponse(responseCode = "404", description = "Client non trouvé")
    })
    @Counted(name = "getClientByIdCount", description = "Nombre d'appels à getClientById")
    public Response getClientById(
            @Parameter(description = "ID du client", required = true) @PathParam("id") Long id) {
        return clientService.getClientById(id)
                .map(client -> Response.ok(client).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Client non trouvé\"}").build());
    }

    @GET
    @Path("/email/{email}")
    @Operation(summary = "Récupérer un client par email", description = "Retourne un client par son email")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Client trouvé", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Client.class))),
            @APIResponse(responseCode = "404", description = "Client non trouvé")
    })
    public Response getClientByEmail(
            @Parameter(description = "Email du client", required = true) @PathParam("email") String email) {
        return clientService.getClientByEmail(email)
                .map(client -> Response.ok(client).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Client non trouvé\"}").build());
    }

    @GET
    @Path("/search")
    @Operation(summary = "Rechercher des clients", description = "Recherche des clients par mot-clé")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Résultats de recherche", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Client.class)))
    })
    public Response searchClients(
            @Parameter(description = "Mot-clé de recherche", required = true) @QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Le mot-clé est requis\"}").build();
        }
        List<Client> clients = clientService.searchClients(keyword);
        return Response.ok(clients).build();
    }

    @POST
    @Operation(summary = "Créer un nouveau client", description = "Ajoute un nouveau client à la base")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Client créé avec succès", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Client.class))),
            @APIResponse(responseCode = "400", description = "Données invalides")
    })
    @Counted(name = "createClientCount", description = "Nombre de clients créés")
    public Response createClient(
            @Parameter(description = "Client à créer", required = true) Client client) {
        if (client == null || client.getEmail() == null || client.getFirstName() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Données du client invalides\"}").build();
        }
        Client createdClient = clientService.createClient(client);
        return Response.status(Response.Status.CREATED).entity(createdClient).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Mettre à jour un client", description = "Modifie un client existant")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Client mis à jour", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Client.class))),
            @APIResponse(responseCode = "404", description = "Client non trouvé")
    })
    @Counted(name = "updateClientCount", description = "Nombre de clients mis à jour")
    public Response updateClient(
            @Parameter(description = "ID du client", required = true) @PathParam("id") Long id,
            @Parameter(description = "Client avec les nouvelles données", required = true) Client client) {
        return clientService.updateClient(id, client)
                .map(updatedClient -> Response.ok(updatedClient).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Client non trouvé\"}").build());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Supprimer un client", description = "Supprime un client de la base")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Client supprimé avec succès"),
            @APIResponse(responseCode = "404", description = "Client non trouvé")
    })
    @Counted(name = "deleteClientCount", description = "Nombre de clients supprimés")
    public Response deleteClient(
            @Parameter(description = "ID du client", required = true) @PathParam("id") Long id) {
        if (clientService.deleteClient(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Client non trouvé\"}").build();
    }

    @GET
    @Path("/count")
    @Operation(summary = "Compter les clients", description = "Retourne le nombre total de clients")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Nombre de clients", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    public Response getClientCount() {
        long count = clientService.getClientCount();
        return Response.ok("{\"count\": " + count + "}").build();
    }
}
