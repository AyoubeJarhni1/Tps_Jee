package ws;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import entity.Livre;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "BibliothequeWS")
public class BibliothequeWS {

    private Map<String, Livre> library = new ConcurrentHashMap<>();

    public BibliothequeWS() {
        library.put("978-3-16-148410-0", new Livre("978-3-16-148410-0", "Le Petit Prince", "Antoine de Saint-Exupéry", new Date()));
        library.put("978-0-14-044913-6", new Livre("978-0-14-044913-6", "Les Misérables", "Victor Hugo", new Date()));
    }

	@WebMethod(operationName = "RechercherLivreParIsbn")
	public Livre rechercherLivreParIsbn(@WebParam(name = "isbn") String isbn) {
		if (isbn == null) return null;
		return library.get(isbn);
	}

	@WebMethod(operationName = "AjouterNouveauLivre")
	public boolean ajouterLivre(@WebParam(name = "livre") Livre livre) {
		if (livre == null || livre.getIsbn() == null || livre.getIsbn().trim().isEmpty()) {
			return false;
		}
		return library.putIfAbsent(livre.getIsbn(), livre) == null;
	}

	@WebMethod(operationName = "ListerLivres")
	public List<Livre> listerLivres() {
		return new ArrayList<>(library.values());
	}
}
