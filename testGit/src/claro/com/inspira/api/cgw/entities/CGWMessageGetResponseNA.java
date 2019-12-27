// 

// 

package claro.com.inspira.api.cgw.entities;

import java.util.List;

public class CGWMessageGetResponseNA
{
    List<CGWMessageDaoNA> messages;
    public long queryTime;
    
    public List<CGWMessageDaoNA> getMessages() {
        return this.messages;
    }
    
    public void setMessages(final List<CGWMessageDaoNA> messages) {
        this.messages = messages;
    }
    
    public long getQueryTime() {
        return this.queryTime;
    }
    
    public void setQueryTime(final long queryTime) {
        this.queryTime = queryTime;
    }
}
