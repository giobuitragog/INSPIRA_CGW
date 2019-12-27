// 

// 

package claro.com.inspira.api.cgw.entities;

public class CGWBox
{
    public String customerId;
    public String customerBox;
    
    public CGWBox() {
    }
    
    public CGWBox(final String costumerId, final String costumerBox) {
        this.customerId = costumerId;
        this.customerBox = costumerBox;
    }
    
    public String getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerBox() {
        return this.customerBox;
    }
    
    public void setCustomerBox(final String customerBox) {
        this.customerBox = customerBox;
    }
}
