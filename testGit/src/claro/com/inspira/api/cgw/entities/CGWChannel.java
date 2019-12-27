// 

// 

package claro.com.inspira.api.cgw.entities;

import java.util.List;

public class CGWChannel
{
    public String messageChannel;
    public List<CGWBox> messageBox;
    
    public String getMessageChannel() {
        return this.messageChannel;
    }
    
    public void setMessageChannel(final String messageChannel) {
        this.messageChannel = messageChannel;
    }
    
    public List<CGWBox> getMessageBox() {
        return this.messageBox;
    }
    
    public void setMessageBox(final List<CGWBox> messageBox) {
        this.messageBox = messageBox;
    }
}
