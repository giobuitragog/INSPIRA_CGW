// 

// 

package claro.com.inspira.api.cgw.entities;

public class GetUrlRequest
{
    public String customerId;
    public String orderId;
    
    public String getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }
    
    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }
}
