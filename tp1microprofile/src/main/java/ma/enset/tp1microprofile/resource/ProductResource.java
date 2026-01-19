package ma.enset.tp1microprofile.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.enset.tp1microprofile.model.Product;
import ma.enset.tp1microprofile.service.ProductService;
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
 * Ressource REST pour la gestion des produits
 */
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Products", description = "Opérations CRUD sur les produits")
public class ProductResource {

    @Inject
    private ProductService productService;

    @GET
    @Operation(summary = "Récupérer tous les produits", description = "Retourne la liste complète des produits")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Liste des produits récupérée avec succès", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Product.class)))
    })
    @Counted(name = "getAllProductsCount", description = "Nombre d'appels à getAllProducts")
    @Timed(name = "getAllProductsTimer", description = "Temps d'exécution de getAllProducts")
    public Response getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Récupérer un produit par ID", description = "Retourne un produit spécifique")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Produit trouvé", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Product.class))),
            @APIResponse(responseCode = "404", description = "Produit non trouvé")
    })
    @Counted(name = "getProductByIdCount", description = "Nombre d'appels à getProductById")
    public Response getProductById(
            @Parameter(description = "ID du produit", required = true) @PathParam("id") Long id) {
        return productService.getProductById(id)
                .map(product -> Response.ok(product).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Produit non trouvé\"}").build());
    }

    @GET
    @Path("/category/{category}")
    @Operation(summary = "Récupérer les produits par catégorie", description = "Retourne tous les produits d'une catégorie")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Produits de la catégorie récupérés", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Product.class)))
    })
    public Response getProductsByCategory(
            @Parameter(description = "Catégorie du produit", required = true) @PathParam("category") String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return Response.ok(products).build();
    }

    @GET
    @Path("/search")
    @Operation(summary = "Rechercher des produits", description = "Recherche des produits par mot-clé")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Résultats de recherche", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Product.class)))
    })
    public Response searchProducts(
            @Parameter(description = "Mot-clé de recherche", required = true) @QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Le mot-clé est requis\"}").build();
        }
        List<Product> products = productService.searchProducts(keyword);
        return Response.ok(products).build();
    }

    @POST
    @Operation(summary = "Créer un nouveau produit", description = "Ajoute un nouveau produit à la base")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Produit créé avec succès", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Product.class))),
            @APIResponse(responseCode = "400", description = "Données invalides")
    })
    @Counted(name = "createProductCount", description = "Nombre de produits créés")
    public Response createProduct(
            @Parameter(description = "Produit à créer", required = true) Product product) {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Données du produit invalides\"}").build();
        }
        Product createdProduct = productService.createProduct(product);
        return Response.status(Response.Status.CREATED).entity(createdProduct).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Mettre à jour un produit", description = "Modifie un produit existant")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Produit mis à jour", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Product.class))),
            @APIResponse(responseCode = "404", description = "Produit non trouvé")
    })
    @Counted(name = "updateProductCount", description = "Nombre de produits mis à jour")
    public Response updateProduct(
            @Parameter(description = "ID du produit", required = true) @PathParam("id") Long id,
            @Parameter(description = "Produit avec les nouvelles données", required = true) Product product) {
        return productService.updateProduct(id, product)
                .map(updatedProduct -> Response.ok(updatedProduct).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Produit non trouvé\"}").build());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Supprimer un produit", description = "Supprime un produit de la base")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Produit supprimé avec succès"),
            @APIResponse(responseCode = "404", description = "Produit non trouvé")
    })
    @Counted(name = "deleteProductCount", description = "Nombre de produits supprimés")
    public Response deleteProduct(
            @Parameter(description = "ID du produit", required = true) @PathParam("id") Long id) {
        if (productService.deleteProduct(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Produit non trouvé\"}").build();
    }

    @GET
    @Path("/count")
    @Operation(summary = "Compter les produits", description = "Retourne le nombre total de produits")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Nombre de produits", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    })
    public Response getProductCount() {
        long count = productService.getProductCount();
        return Response.ok("{\"count\": " + count + "}").build();
    }
}
