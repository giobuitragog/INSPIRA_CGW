// 

// 

package claro.com.inspira.api.cgw.pojo;

public class CGWMsgSchema
{
    String contentId;
    String contentName;
    String content;
    String variablesSet;
    String cgwProfile;
    String TYPE_SCH;
    String resourceContent;
    
    public String getTYPE_SCH() {
        return this.TYPE_SCH;
    }
    
    public void setTYPE_SCH(final String tYPE_SCH) {
        this.TYPE_SCH = tYPE_SCH;
    }
    
    public String getContentId() {
        return this.contentId;
    }
    
    public void setContentId(final String contentId) {
        this.contentId = contentId;
    }
    
    public String getContentName() {
        return this.contentName;
    }
    
    public void setContentName(final String contentName) {
        this.contentName = contentName;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public String getVariablesSet() {
        return this.variablesSet;
    }
    
    public void setVariablesSet(final String variablesSet) {
        this.variablesSet = variablesSet;
    }
    
    public String getCgwProfile() {
        return this.cgwProfile;
    }
    
    public void setCgwProfile(final String cgwProfile) {
        this.cgwProfile = cgwProfile;
    }
    
    public String getResourceContent() {
        return this.resourceContent;
    }
    
    public void setResourceContent(final String resourceContent) {
        this.resourceContent = resourceContent;
    }
}
