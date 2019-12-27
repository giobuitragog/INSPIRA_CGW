// 

// 

package claro.com.inspira.api.cgw.sender;

import claro.com.inspira.api.cgw.pojo.CGWMsgSchema;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;

public interface NotifyProtocolInterface
{
    void setRequest(final CGWMessageRequest p0);
    
    String send() throws Exception;
    
    void persistLog();
    
    void validate() throws Exception;
    
    void setSchema(final CGWMsgSchema p0);
}
