// 

// 

package claro.com.inspira.api.cgw.ericsson;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ngwRequest", propOrder = { "request" }, namespace = "ngwRequest")
public class NgwRequest
{
    @XmlElement(required = true)
    protected List<MapRequest> request;
    
    public List<MapRequest> getRequest() {
        if (this.request == null) {
            this.request = new ArrayList<MapRequest>();
        }
        return this.request;
    }
}
