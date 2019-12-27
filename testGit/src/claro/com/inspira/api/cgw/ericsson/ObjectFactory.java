// 

// 

package claro.com.inspira.api.cgw.ericsson;

import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory
{
    private static final QName _ServiceFailedException_QNAME;
    private static final QName _NgwRequest_QNAME;
    private static final QName _NgwResponse_QNAME;
    
    static {
        _ServiceFailedException_QNAME = new QName("http://webapps.online.ericsson.com", "ServiceFailedException");
        _NgwRequest_QNAME = new QName("http://webapps.online.ericsson.com", "ngwRequest");
        _NgwResponse_QNAME = new QName("http://webapps.online.ericsson.com", "ngwResponse");
    }
    
    public ServiceFailedException createServiceFailedException() {
        return new ServiceFailedException();
    }
    
    public NgwRequest createNgwRequest() {
        return new NgwRequest();
    }
    
    public NgwResponse createNgwResponse() {
        return new NgwResponse();
    }
    
    public MapRequest createMapRequest() {
        return new MapRequest();
    }
    
    public MapItem createMapItem() {
        return new MapItem();
    }
    
    public MapResponse createMapResponse() {
        return new MapResponse();
    }
    
    @XmlElementDecl(namespace = "http://webapps.online.ericsson.com", name = "ServiceFailedException")
    public JAXBElement<ServiceFailedException> createServiceFailedException(final ServiceFailedException value) {
        return (JAXBElement<ServiceFailedException>)new JAXBElement(ObjectFactory._ServiceFailedException_QNAME, (Class)ServiceFailedException.class, (Class)null, (Object)value);
    }
    
    @XmlElementDecl(namespace = "http://webapps.online.ericsson.com", name = "ngwRequest")
    public JAXBElement<NgwRequest> createNgwRequest(final NgwRequest value) {
        return (JAXBElement<NgwRequest>)new JAXBElement(ObjectFactory._NgwRequest_QNAME, (Class)NgwRequest.class, (Class)null, (Object)value);
    }
    
    @XmlElementDecl(namespace = "http://webapps.online.ericsson.com", name = "ngwResponse")
    public JAXBElement<NgwResponse> createNgwResponse(final NgwResponse value) {
        return (JAXBElement<NgwResponse>)new JAXBElement(ObjectFactory._NgwResponse_QNAME, (Class)NgwResponse.class, (Class)null, (Object)value);
    }
}
