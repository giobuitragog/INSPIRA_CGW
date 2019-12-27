// 

// 

package claro.com.inspira.api.msexch.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MSExchangeResponse")
public class MSExchangeResponse
{
    public boolean isValid;
    public String message;
    
    public void setIsValid(final boolean isValid) {
        this.isValid = isValid;
    }
    
    @XmlElement(required = true)
    public boolean isIsValid() {
        return this.isValid;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    @XmlElement(required = true)
    public String getMessage() {
        return this.message;
    }
}
