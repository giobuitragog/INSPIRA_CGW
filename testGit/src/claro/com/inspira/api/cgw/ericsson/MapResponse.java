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
@XmlType(name = "mapResponse", propOrder = { "responseID", "result" })
public class MapResponse
{
    protected int responseID;
    @XmlElement(required = true)
    protected List<MapItem> result;
    
    public int getResponseID() {
        return this.responseID;
    }
    
    public void setResponseID(final int value) {
        this.responseID = value;
    }
    
    public List<MapItem> getResult() {
        if (this.result == null) {
            this.result = new ArrayList<MapItem>();
        }
        return this.result;
    }
}
