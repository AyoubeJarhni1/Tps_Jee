
package proxy;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the proxy package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AjouterNouveauLivre_QNAME = new QName("http://ws/", "AjouterNouveauLivre");
    private final static QName _AjouterNouveauLivreResponse_QNAME = new QName("http://ws/", "AjouterNouveauLivreResponse");
    private final static QName _ListerLivres_QNAME = new QName("http://ws/", "ListerLivres");
    private final static QName _ListerLivresResponse_QNAME = new QName("http://ws/", "ListerLivresResponse");
    private final static QName _RechercherLivreParIsbn_QNAME = new QName("http://ws/", "RechercherLivreParIsbn");
    private final static QName _RechercherLivreParIsbnResponse_QNAME = new QName("http://ws/", "RechercherLivreParIsbnResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: proxy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AjouterNouveauLivre }
     * 
     */
    public AjouterNouveauLivre createAjouterNouveauLivre() {
        return new AjouterNouveauLivre();
    }

    /**
     * Create an instance of {@link AjouterNouveauLivreResponse }
     * 
     */
    public AjouterNouveauLivreResponse createAjouterNouveauLivreResponse() {
        return new AjouterNouveauLivreResponse();
    }

    /**
     * Create an instance of {@link ListerLivres }
     * 
     */
    public ListerLivres createListerLivres() {
        return new ListerLivres();
    }

    /**
     * Create an instance of {@link ListerLivresResponse }
     * 
     */
    public ListerLivresResponse createListerLivresResponse() {
        return new ListerLivresResponse();
    }

    /**
     * Create an instance of {@link RechercherLivreParIsbn }
     * 
     */
    public RechercherLivreParIsbn createRechercherLivreParIsbn() {
        return new RechercherLivreParIsbn();
    }

    /**
     * Create an instance of {@link RechercherLivreParIsbnResponse }
     * 
     */
    public RechercherLivreParIsbnResponse createRechercherLivreParIsbnResponse() {
        return new RechercherLivreParIsbnResponse();
    }

    /**
     * Create an instance of {@link Livre }
     * 
     */
    public Livre createLivre() {
        return new Livre();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterNouveauLivre }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AjouterNouveauLivre }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "AjouterNouveauLivre")
    public JAXBElement<AjouterNouveauLivre> createAjouterNouveauLivre(AjouterNouveauLivre value) {
        return new JAXBElement<AjouterNouveauLivre>(_AjouterNouveauLivre_QNAME, AjouterNouveauLivre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterNouveauLivreResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AjouterNouveauLivreResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "AjouterNouveauLivreResponse")
    public JAXBElement<AjouterNouveauLivreResponse> createAjouterNouveauLivreResponse(AjouterNouveauLivreResponse value) {
        return new JAXBElement<AjouterNouveauLivreResponse>(_AjouterNouveauLivreResponse_QNAME, AjouterNouveauLivreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListerLivres }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListerLivres }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "ListerLivres")
    public JAXBElement<ListerLivres> createListerLivres(ListerLivres value) {
        return new JAXBElement<ListerLivres>(_ListerLivres_QNAME, ListerLivres.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListerLivresResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListerLivresResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "ListerLivresResponse")
    public JAXBElement<ListerLivresResponse> createListerLivresResponse(ListerLivresResponse value) {
        return new JAXBElement<ListerLivresResponse>(_ListerLivresResponse_QNAME, ListerLivresResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercherLivreParIsbn }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RechercherLivreParIsbn }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "RechercherLivreParIsbn")
    public JAXBElement<RechercherLivreParIsbn> createRechercherLivreParIsbn(RechercherLivreParIsbn value) {
        return new JAXBElement<RechercherLivreParIsbn>(_RechercherLivreParIsbn_QNAME, RechercherLivreParIsbn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercherLivreParIsbnResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RechercherLivreParIsbnResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws/", name = "RechercherLivreParIsbnResponse")
    public JAXBElement<RechercherLivreParIsbnResponse> createRechercherLivreParIsbnResponse(RechercherLivreParIsbnResponse value) {
        return new JAXBElement<RechercherLivreParIsbnResponse>(_RechercherLivreParIsbnResponse_QNAME, RechercherLivreParIsbnResponse.class, null, value);
    }

}
