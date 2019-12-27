// 

// 

package claro.com.inspira.api.cgw.entities;

import claro.com.inspira.api.common.entities.AttachInterface;
import claro.com.inspira.api.common.entities.BasicAttach;
import java.util.List;

public class CGWMessageGetRequest
{
    public List<String> idMessage;
    public long startDate;
    public long endDate;
    public String customerId;
    public String messageChannel;
    public String profileId;
    public String content;
    public String contentType;
    public List<String> communicationType;
    public List<String> communicationOrigin;
    public BasicAttach attach;
    public long page;
    public String state;
    public String stateDate;
    public boolean getAttachContent;
    public List<String> messageBox;
    
    public String getState() {
        return this.state;
    }
    
    public void setState(final String state) {
        this.state = state;
    }
    
    public String getStateDate() {
        return this.stateDate;
    }
    
    public void setStateDate(final String stateDate) {
        this.stateDate = stateDate;
    }
    
    public List<String> getIdMessage() {
        return this.idMessage;
    }
    
    public void setIdMessage(final List<String> idMessage) {
        this.idMessage = idMessage;
    }
    
    public long getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(final long startDate) {
        this.startDate = startDate;
    }
    
    public long getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(final long endDate) {
        this.endDate = endDate;
    }
    
    public String getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }
    
    public String getMessageChannel() {
        return this.messageChannel;
    }
    
    public void setMessageChannel(final String messageChannel) {
        this.messageChannel = messageChannel;
    }
    
    public String getProfileId() {
        return this.profileId;
    }
    
    public void setProfileId(final String profileId) {
        this.profileId = profileId;
    }
    
    public List<String> getCommunicationType() {
        return this.communicationType;
    }
    
    public void setCommunicationType(final List<String> communicationType) {
        this.communicationType = communicationType;
    }
    
    public List<String> getCommunicationOrigin() {
        return this.communicationOrigin;
    }
    
    public void setCommunicationOrigin(final List<String> communicationOrigin) {
        this.communicationOrigin = communicationOrigin;
    }
    
    public long getPage() {
        return this.page;
    }
    
    public void setPage(final long page) {
        this.page = page;
    }
    
    public AttachInterface getAttach() {
        return this.attach;
    }
    
    public void setAttach(final BasicAttach attach) {
        this.attach = attach;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    
    public boolean getGetAttachContent() {
        return this.getAttachContent;
    }
    
    public void setGetAttachContent(final boolean getAttachContent) {
        this.getAttachContent = getAttachContent;
    }
    
    public List<String> getMessageBox() {
        return this.messageBox;
    }
    
    public void setMessageBox(final List<String> messageBox) {
        this.messageBox = messageBox;
    }
}
