// 

// 

package claro.com.inspira.api.common.entities;

public class BasicAttach implements AttachInterface
{
    public String name;
    public String type;
    public String encode;
    public String content;
    public String content2;
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public String getType() {
        return this.type;
    }
    
    @Override
    public void setType(final String type) {
        this.type = type;
    }
    
    @Override
    public String getEncode() {
        return this.encode;
    }
    
    @Override
    public void setEncode(final String encode) {
        this.encode = encode;
    }
    
    @Override
    public Object getContent() throws Exception {
        return this.content;
    }
    
    @Override
    public void setContent(final String content) {
    }
    
    @Override
    public String getContent2() {
        return this.content2;
    }
    
    @Override
    public void setContent2(final String content2) {
    }
}
