// 

// 

package claro.com.inspira.api.cgw.entities;

import java.util.List;

public class CGWMessageDao
{
    public String _id;
    public String idMessage;
    public String pushType;
    public String typeCostumer;
    public List<String> customerId;
    public List<CGWChannel> messageBox;
    public List<String> profileId;
    public List<String> communicationType;
    public String communicationOrigin;
    public String deliveryReceipts;
    public String contentType;
    public String messageContent;
    public Double dateTime;
    public Double requestTime;
    public Double sendTime;
    public String state;
    public String stateDate;
    public String subject;
    
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(final String subject) {
        this.subject = subject;
    }
    
    public String getPushType() {
        return this.pushType;
    }
    
    public void setPushType(final String pushType) {
        this.pushType = pushType;
    }
    
    public String getTypeCostumer() {
        return this.typeCostumer;
    }
    
    public void setTypeCostumer(final String typeCostumer) {
        this.typeCostumer = typeCostumer;
    }
    
    public List<String> getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(final List<String> customerId) {
        this.customerId = customerId;
    }
    
    public List<CGWChannel> getMessageBox() {
        return this.messageBox;
    }
    
    public void setMessageBox(final List<CGWChannel> messageBox) {
        this.messageBox = messageBox;
    }
    
    public List<String> getProfileId() {
        return this.profileId;
    }
    
    public void setProfileId(final List<String> profileId) {
        this.profileId = profileId;
    }
    
    public List<String> getCommunicationType() {
        return this.communicationType;
    }
    
    public void setCommunicationType(final List<String> communicationType) {
        this.communicationType = communicationType;
    }
    
    public String getCommunicationOrigin() {
        return this.communicationOrigin;
    }
    
    public void setCommunicationOrigin(final String communicationOrigin) {
        this.communicationOrigin = communicationOrigin;
    }
    
    public String getDeliveryReceipts() {
        return this.deliveryReceipts;
    }
    
    public void setDeliveryReceipts(final String deliveryReceipts) {
        this.deliveryReceipts = deliveryReceipts;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    
    public String getMessageContent() {
        return this.messageContent;
    }
    
    public void setMessageContent(final String messageContent) {
        this.messageContent = messageContent;
    }
    
    public Double getRequestTime() {
        return this.requestTime;
    }
    
    public void setRequestTime(final Double requestTime) {
        this.requestTime = requestTime;
    }
    
    public Double getSendTime() {
        return this.sendTime;
    }
    
    public void setSendTime(final Double sendTime) {
        this.sendTime = sendTime;
    }
    
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
    
    public String getIdMessage() {
        return this.idMessage;
    }
    
    public void setIdMessage(final String idMessage) {
        this.idMessage = idMessage;
    }
    
    public String get_id() {
        return this._id;
    }
    
    public void set_id(final String _id) {
        this._id = _id;
    }
    
    public Double getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(final Double dateTime) {
        this.dateTime = dateTime;
    }
}
