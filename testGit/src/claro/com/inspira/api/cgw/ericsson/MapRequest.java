// 

// 

package claro.com.inspira.api.cgw.ericsson;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mapRequest", propOrder = { "requestID", "operation", "origin", "msisdn", "item" })
public class MapRequest
{
    protected int requestID;
    @XmlElement(required = true)
    protected String operation;
    @XmlElement(required = true)
    protected String origin;
    @XmlElement(required = true)
    protected String msisdn;
    @XmlElement(required = true)
    protected List<MapItem> item;
    
    public int getRequestID() {
        return this.requestID;
    }
    
    public void setRequestID(final int value) {
        this.requestID = value;
    }
    
    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(final String value) {
        this.operation = value;
    }
    
    public String getOrigin() {
        return this.origin;
    }
    
    public void setOrigin(final String value) {
        this.origin = value;
    }
    
    public String getMsisdn() {
        return this.msisdn;
    }
    
    public void setMsisdn(final String value) {
        this.msisdn = value;
    }
    
    public List<MapItem> getItem() {
        if (this.item == null) {
            this.item = new ArrayList<MapItem>();
        }
        return this.item;
    }
    
    public void setItem(final List<MapItem> item) {
        this.item = item;
    }
}
