package com.ensa.gestionuser.services;

import com.ensa.gestionuser.models.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

    private static final List<User> users = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private static int nextId = 1;

    static {
        users.add(new User(nextId++, "Khadija", "khadija@example.com"));
        users.add(new User(nextId++, "Hajar", "hajar@example.com"));
        LOGGER.info("Données initiales : 2 utilisateurs chargés");
    }

    @GET
    public List<User> getAllUsers() {
        LOGGER.info("GET /api/users → " + users.size() + " utilisateurs");
        return new ArrayList<>(users);
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int id) {
        LOGGER.info("GET /api/users/" + id);
        for (User u : users) {
            if (u.getId() == id) return Response.ok(u).build();
        }
        LOGGER.warning("Utilisateur non trouvé : ID = " + id);
        return Response.status(404).entity(Map.of("message", "Utilisateur non trouvé")).build();
    }

    @POST
    public Response createUser(User user) {
        if (user.getNom() == null || user.getEmail() == null) {
            return Response.status(400).entity(Map.of("message", "Paramètre nom requis")).build();
        }
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                return Response.status(409).entity(Map.of("message", "Email déjà utilisé")).build();
            }
        }
        user.setId(nextId++);
        users.add(user);
        LOGGER.info("Utilisateur créé : " + user.getNom());
        return Response.status(201).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int id, User updated) {
        for (User u : users) {
            if (u.getId() == id) {
                u.setNom(updated.getNom());
                u.setEmail(updated.getEmail());
                return Response.ok(u).build();
            }
        }
        return Response.status(404).entity(Map.of("message", "Utilisateur non trouvé")).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        users.removeIf(u -> u.getId() == id);
        if (users.stream().noneMatch(u -> u.getId() == id)) {
            LOGGER.info("Utilisateur supprimé : ID = " + id);
            return Response.ok(Map.of("message", "Utilisateur supprimé avec succès")).build();
        }
        return Response.status(404).entity(Map.of("message", "Utilisateur non trouvé")).build();
    }
}