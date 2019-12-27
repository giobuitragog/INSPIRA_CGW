// 

// 

package claro.com.inspira.api.cgw.sms;

import javax.xml.ws.FaultAction;
import javax.xml.ws.Action;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.RequestWrapper;
import javax.jws.WebResult;
import javax.jws.WebMethod;
import claro.com.inspira.api.cgw.ericsson.MapResponse;
import javax.jws.WebParam;
import claro.com.inspira.api.cgw.ericsson.MapRequest;
import java.util.List;
import claro.com.inspira.api.cgw.ericsson.ObjectFactory;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.jws.WebService;

@WebService(name = "ngwGeneralPortType", targetNamespace = "http://webapps.online.ericsson.com")
@XmlSeeAlso({ ObjectFactory.class })
public interface NgwGeneralPortType
{
    @WebMethod
    @WebResult(name = "response", targetNamespace = "http://webapps.online.ericsson.com")
    @RequestWrapper(localName = "ngwRequest", targetNamespace = "http://webapps.online.ericsson.com", className = "com.ericsson.online.webapps.NgwRequest")
    @ResponseWrapper(localName = "ngwResponse", targetNamespace = "http://webapps.online.ericsson.com", className = "com.ericsson.online.webapps.NgwResponse")
    @Action(input = "http://webapps.online.ericsson.com/ngwGeneralPortType/ngwRequestRequest", output = "http://webapps.online.ericsson.com/ngwGeneralPortType/ngwRequestResponse", fault = { @FaultAction(className = ServiceFailedExceptionException.class, value = "http://webapps.online.ericsson.com/ngwGeneralPortType/ngwRequest/Fault/ServiceFailedException_Exception") })
    List<MapResponse> ngwRequest(@WebParam(name = "request", targetNamespace = "http://webapps.online.ericsson.com") final List<MapRequest> p0) throws ServiceFailedExceptionException;
}
