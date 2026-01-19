# TP1 MicroProfile - Application de Gestion

## Description
Application MicroProfile démontrant l'utilisation de JAX-RS, CDI, MicroProfile Config, Health, Metrics et OpenAPI pour gérer des produits et des clients.

## Fonctionnalités

### 🎯 Technologies Utilisées
- **MicroProfile 5.0**
- **Jakarta EE 9.1**
- **JAX-RS 3.0** - API REST
- **CDI 3.0** - Injection de dépendances
- **JSON-B 2.0** - Sérialisation JSON
- **MicroProfile Config** - Configuration externalisée
- **MicroProfile Health** - Health checks
- **MicroProfile Metrics** - Métriques applicatives
- **MicroProfile OpenAPI** - Documentation API

### 📦 Entités
- **Product** - Gestion de produits (id, name, description, price, quantity, category)
- **Client** - Gestion de clients (id, firstName, lastName, email, phone, address)

### 🔌 Endpoints REST

#### Produits (`/api/products`)
- `GET /api/products` - Liste tous les produits
- `GET /api/products/{id}` - Récupère un produit par ID
- `GET /api/products/category/{category}` - Produits par catégorie
- `GET /api/products/search?keyword={keyword}` - Recherche de produits
- `POST /api/products` - Crée un nouveau produit
- `PUT /api/products/{id}` - Met à jour un produit
- `DELETE /api/products/{id}` - Supprime un produit
- `GET /api/products/count` - Compte les produits

#### Clients (`/api/clients`)
- `GET /api/clients` - Liste tous les clients
- `GET /api/clients/{id}` - Récupère un client par ID
- `GET /api/clients/email/{email}` - Client par email
- `GET /api/clients/search?keyword={keyword}` - Recherche de clients
- `POST /api/clients` - Crée un nouveau client
- `PUT /api/clients/{id}` - Met à jour un client
- `DELETE /api/clients/{id}` - Supprime un client
- `GET /api/clients/count` - Compte les clients

#### Configuration (`/api/config`)
- `GET /api/config` - Récupère la configuration de l'application

### 🏥 MicroProfile Health
- `/health` - État général de santé
- `/health/live` - Liveness probe
- `/health/ready` - Readiness probe
- `/health/started` - Startup probe

### 📊 MicroProfile Metrics
- `/metrics` - Toutes les métriques
- `/metrics/application` - Métriques applicatives
- `/metrics/base` - Métriques de base JVM
- `/metrics/vendor` - Métriques du serveur

### 📖 OpenAPI Documentation
- `/openapi` - Spécification OpenAPI (JSON/YAML)
- `/openapi/ui` - Interface Swagger UI

## 🚀 Installation et Déploiement

### Prérequis
- Java 11 ou supérieur
- Maven 3.6+
- Open Liberty (géré par le plugin Maven)

### Compilation
```bash
mvn clean package
```

### Déploiement avec Open Liberty
```bash
mvn liberty:dev
```

L'application sera accessible sur : `http://localhost:9080`

### Arrêt du serveur
```bash
mvn liberty:stop
```

## 🧪 Tests

### Tester les endpoints avec curl

#### Produits
```bash
# Liste des produits
curl http://localhost:9080/api/products

# Créer un produit
curl -X POST http://localhost:9080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Nouveau Produit","description":"Description","price":99.99,"quantity":10,"category":"Test"}'

# Récupérer un produit
curl http://localhost:9080/api/products/1

# Mettre à jour un produit
curl -X PUT http://localhost:9080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Produit Modifié","description":"Nouvelle description","price":149.99,"quantity":20,"category":"Test"}'

# Supprimer un produit
curl -X DELETE http://localhost:9080/api/products/1
```

#### Clients
```bash
# Liste des clients
curl http://localhost:9080/api/clients

# Créer un client
curl -X POST http://localhost:9080/api/clients \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Ahmed","lastName":"Benjelloun","email":"a.benjelloun@email.com","phone":"0656789012","address":"Tanger, Maroc"}'
```

#### Health Checks
```bash
curl http://localhost:9080/health
curl http://localhost:9080/health/live
curl http://localhost:9080/health/ready
```

#### Metrics
```bash
curl http://localhost:9080/metrics
curl http://localhost:9080/metrics/application
```

## 📁 Structure du Projet
```
tp1microprofile/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ma/enset/tp1microprofile/
│   │   │       ├── model/           # Entités (Product, Client)
│   │   │       ├── service/         # Services métier
│   │   │       ├── resource/        # Endpoints REST
│   │   │       ├── config/          # Configuration
│   │   │       ├── health/          # Health checks
│   │   │       └── RestApplication.java
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── microprofile-config.properties
│   │   ├── liberty/
│   │   │   └── config/
│   │   │       └── server.xml
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── beans.xml
│   │       └── index.html
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```

## 🎓 Concepts MicroProfile Illustrés

### 1. **JAX-RS (REST API)**
- Création d'endpoints RESTful
- Annotations `@Path`, `@GET`, `@POST`, `@PUT`, `@DELETE`
- Content negotiation avec `@Produces` et `@Consumes`

### 2. **CDI (Contexts and Dependency Injection)**
- Injection de dépendances avec `@Inject`
- Beans avec scope `@ApplicationScoped`

### 3. **MicroProfile Config**
- Configuration externalisée avec `@ConfigProperty`
- Fichier `microprofile-config.properties`
- Valeurs par défaut

### 4. **MicroProfile Health**
- `@Liveness` - L'application est vivante
- `@Readiness` - L'application est prête
- `@Startup` - L'application a démarré

### 5. **MicroProfile Metrics**
- `@Counted` - Compteur d'appels
- `@Timed` - Temps d'exécution

### 6. **MicroProfile OpenAPI**
- Documentation automatique de l'API
- Annotations `@Operation`, `@APIResponse`, `@Tag`
- Swagger UI intégré

## 👨‍💻 Auteur
ENSET - TP MicroProfile

## 📝 Licence
Projet éducatif - ENSET
