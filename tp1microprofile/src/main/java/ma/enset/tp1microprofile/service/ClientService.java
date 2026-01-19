package ma.enset.tp1microprofile.service;

import jakarta.enterprise.context.ApplicationScoped;
import ma.enset.tp1microprofile.model.Client;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Service pour la gestion des clients
 */
@ApplicationScoped
public class ClientService {

    private final Map<Long, Client> clients = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public ClientService() {
        // Initialisation avec quelques clients
        initializeData();
    }

    private void initializeData() {
        createClient(new Client(null, "Mohammed", "Alami", "m.alami@email.com", "0612345678", "Casablanca, Maroc"));
        createClient(new Client(null, "Fatima", "Benali", "f.benali@email.com", "0623456789", "Rabat, Maroc"));
        createClient(new Client(null, "Youssef", "El Idrissi", "y.elidrissi@email.com", "0634567890", "Fès, Maroc"));
        createClient(new Client(null, "Amina", "Zahra", "a.zahra@email.com", "0645678901", "Marrakech, Maroc"));
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    public Optional<Client> getClientById(Long id) {
        return Optional.ofNullable(clients.get(id));
    }

    public Optional<Client> getClientByEmail(String email) {
        return clients.values().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Client createClient(Client client) {
        Long id = idGenerator.incrementAndGet();
        client.setId(id);
        clients.put(id, client);
        return client;
    }

    public Optional<Client> updateClient(Long id, Client client) {
        if (!clients.containsKey(id)) {
            return Optional.empty();
        }
        client.setId(id);
        clients.put(id, client);
        return Optional.of(client);
    }

    public boolean deleteClient(Long id) {
        return clients.remove(id) != null;
    }

    public long getClientCount() {
        return clients.size();
    }

    public List<Client> searchClients(String keyword) {
        return clients.values().stream()
                .filter(c -> c.getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                        c.getLastName().toLowerCase().contains(keyword.toLowerCase()) ||
                        c.getEmail().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
