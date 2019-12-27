// 

// 

package claro.com.inspira.api.cgw.ericsson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mapItem", propOrder = { "key", "value" })
public class MapItem
{
    @XmlElement(required = true)
    protected String key;
    @XmlElement(required = true)
    protected String value;
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(final String value) {
        this.key = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
}
