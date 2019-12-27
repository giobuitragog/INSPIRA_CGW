// 

// 

package claro.com.inspira.api.cgw.sms;

import claro.com.inspira.api.cgw.ericsson.ServiceFailedException;
import javax.xml.ws.WebFault;

@WebFault(name = "ServiceFailedException", targetNamespace = "http://webapps.online.ericsson.com")
public class ServiceFailedExceptionException extends Exception
{
    private ServiceFailedException faultInfo;
    
    public ServiceFailedExceptionException(final String message, final ServiceFailedException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }
    
    public ServiceFailedExceptionException(final String message, final ServiceFailedException faultInfo, final Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }
    
    public ServiceFailedException getFaultInfo() {
        return this.faultInfo;
    }
}
