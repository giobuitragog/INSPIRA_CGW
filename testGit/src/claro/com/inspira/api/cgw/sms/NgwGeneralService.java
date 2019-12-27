// 

// 

package claro.com.inspira.api.cgw.sms;

import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;
import java.net.URL;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.Service;

@WebServiceClient(name = "NgwGeneralService", targetNamespace = "http://webapps.online.ericsson.com", wsdlLocation = "http://172.24.20.8:1090/ngw/webservice.ws?wsdl#%7Bhttp%3A%2F%2Fwebapps.online.ericsson.com%7DNgwGeneralService")
public class NgwGeneralService extends Service
{
    private static final URL NGWGENERALSERVICE_WSDL_LOCATION;
    private static final WebServiceException NGWGENERALSERVICE_EXCEPTION;
    private static final QName NGWGENERALSERVICE_QNAME;
    
    static {
        NGWGENERALSERVICE_QNAME = new QName("http://webapps.online.ericsson.com", "NgwGeneralService");
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://172.24.20.8:1090/ngw/webservice.ws?wsdl#%7Bhttp%3A%2F%2Fwebapps.online.ericsson.com%7DNgwGeneralService");
        }
        catch (MalformedURLException ex) {
            e = new WebServiceException((Throwable)ex);
        }
        NGWGENERALSERVICE_WSDL_LOCATION = url;
        NGWGENERALSERVICE_EXCEPTION = e;
    }
    
    public NgwGeneralService() {
        super(__getWsdlLocation(), NgwGeneralService.NGWGENERALSERVICE_QNAME);
    }
    
    public NgwGeneralService(final WebServiceFeature... features) {
        super(__getWsdlLocation(), NgwGeneralService.NGWGENERALSERVICE_QNAME, features);
    }
    
    public NgwGeneralService(final URL wsdlLocation) {
        super(wsdlLocation, NgwGeneralService.NGWGENERALSERVICE_QNAME);
    }
    
    public NgwGeneralService(final URL wsdlLocation, final WebServiceFeature... features) {
        super(wsdlLocation, NgwGeneralService.NGWGENERALSERVICE_QNAME, features);
    }
    
    public NgwGeneralService(final URL wsdlLocation, final QName serviceName) {
        super(wsdlLocation, serviceName);
    }
    
    public NgwGeneralService(final URL wsdlLocation, final QName serviceName, final WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }
    
    @WebEndpoint(name = "NgwGeneralPort")
    public NgwGeneralPortType getNgwGeneralPort() {
        return (NgwGeneralPortType)super.getPort(new QName("http://webapps.online.ericsson.com", "NgwGeneralPort"), (Class)NgwGeneralPortType.class);
    }
    
    @WebEndpoint(name = "NgwGeneralPort")
    public NgwGeneralPortType getNgwGeneralPort(final WebServiceFeature... features) {
        return (NgwGeneralPortType)super.getPort(new QName("http://webapps.online.ericsson.com", "NgwGeneralPort"), (Class)NgwGeneralPortType.class, features);
    }
    
    private static URL __getWsdlLocation() {
        if (NgwGeneralService.NGWGENERALSERVICE_EXCEPTION != null) {
            throw NgwGeneralService.NGWGENERALSERVICE_EXCEPTION;
        }
        return NgwGeneralService.NGWGENERALSERVICE_WSDL_LOCATION;
    }
}
