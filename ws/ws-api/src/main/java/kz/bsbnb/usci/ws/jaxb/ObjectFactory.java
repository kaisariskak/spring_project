
package kz.bsbnb.usci.ws.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kz.bsbnb.usci.ws.jaxb package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kz.bsbnb.usci.ws.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAuthTokenRequest }
     * 
     */
    public GetAuthTokenRequest createGetAuthTokenRequest() {
        return new GetAuthTokenRequest();
    }

    /**
     * Create an instance of {@link RequestInfo }
     * 
     */
    public RequestInfo createRequestInfo() {
        return new RequestInfo();
    }

    /**
     * Create an instance of {@link GetUsciEntitiesResponse }
     * 
     */
    public GetUsciEntitiesResponse createGetUsciEntitiesResponse() {
        return new GetUsciEntitiesResponse();
    }

    /**
     * Create an instance of {@link ResponseInfo }
     * 
     */
    public ResponseInfo createResponseInfo() {
        return new ResponseInfo();
    }

    /**
     * Create an instance of {@link Manifest }
     * 
     */
    public Manifest createManifest() {
        return new Manifest();
    }

    /**
     * Create an instance of {@link GetAuthTokenResponse }
     * 
     */
    public GetAuthTokenResponse createGetAuthTokenResponse() {
        return new GetAuthTokenResponse();
    }

    /**
     * Create an instance of {@link GetUsciEntitiesRequest }
     * 
     */
    public GetUsciEntitiesRequest createGetUsciEntitiesRequest() {
        return new GetUsciEntitiesRequest();
    }

}
