// 

// 

package claro.com.inspira.api.cgw.entities;

import java.util.Map;
import claro.com.inspira.api.common.entities.Attach;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class CGWMessageRequest
{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String pushType;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String typeCostumer;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> customerId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<CGWChannel> messageBox;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> profileId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> communicationType;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String communicationOrigin;
    public String deliveryReceipts;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String contentType;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String messageContent;
    public List<Attach> attach;
    public Long dateTime;
    public String idMessage;
    public String state;
    public String stateDate;
    public String subject;
    public Map variableMap;
    
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(final String subject) {
        this.subject = subject;
    }
    
    public String getIdMessage() {
        return this.idMessage;
    }
    
    public void setIdMessage(final String idMessage) {
        this.idMessage = idMessage;
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
    
    public String getTypeCostumer() {
        return this.typeCostumer;
    }
    
    public void setTypeCostumer(final String typeCostumer) {
        this.typeCostumer = typeCostumer;
    }
    
    public List<CGWChannel> getMessageBox() {
        return this.messageBox;
    }
    
    public void setMessageBoxes(final List<CGWChannel> messageBox) {
        this.messageBox = messageBox;
    }
    
    public String getPushType() {
        return this.pushType;
    }
    
    public void setPushType(final String pushType) {
        this.pushType = pushType;
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
    
    public List<Attach> getAttach() {
        return this.attach;
    }
    
    public void setAttach(final List<Attach> attach) {
        this.attach = attach;
    }
    
    public Long getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(final Long dateTime) {
        this.dateTime = dateTime;
    }
    
    public List<String> getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(final List<String> customerId) {
        this.customerId = customerId;
    }
    
    public void setMessageBox(final List<CGWChannel> messageBox) {
        this.messageBox = messageBox;
    }
    
    public Map getVariableMap() {
        return this.variableMap;
    }
    
    public void setVariableMap(final Map variableMap) {
        this.variableMap = variableMap;
    }
}
