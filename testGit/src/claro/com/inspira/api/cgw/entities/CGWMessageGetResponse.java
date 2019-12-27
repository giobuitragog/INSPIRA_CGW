// 

// 

package claro.com.inspira.api.cgw.entities;

import java.util.List;

public class CGWMessageGetResponse
{
    List<CGWMessageDao> messages;
    public long queryTime;
    
    public List<CGWMessageDao> getMessages() {
        return this.messages;
    }
    
    public void setMessages(final List<CGWMessageDao> messages) {
        this.messages = messages;
    }
    
    public long getQueryTime() {
        return this.queryTime;
    }
    
    public void setQueryTime(final long queryTime) {
        this.queryTime = queryTime;
    }
}
