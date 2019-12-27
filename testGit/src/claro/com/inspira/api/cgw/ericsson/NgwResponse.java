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
@XmlType(name = "ngwResponse", propOrder = { "response" }, namespace = "ngwResponse")
public class NgwResponse
{
    @XmlElement(required = true)
    protected List<MapResponse> response;
    
    public List<MapResponse> getResponse() {
        if (this.response == null) {
            this.response = new ArrayList<MapResponse>();
        }
        return this.response;
    }
}
