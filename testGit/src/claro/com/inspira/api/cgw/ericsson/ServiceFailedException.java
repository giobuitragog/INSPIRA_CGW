// 

// 

package claro.com.inspira.api.cgw.ericsson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFailedException", propOrder = { "faultInfo", "message" })
public class ServiceFailedException
{
    @XmlElement(required = true)
    protected String faultInfo;
    @XmlElement(required = true)
    protected String message;
    
    public String getFaultInfo() {
        return this.faultInfo;
    }
    
    public void setFaultInfo(final String value) {
        this.faultInfo = value;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String value) {
        this.message = value;
    }
}
