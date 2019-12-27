// 

// 

package claro.com.inspira.api.common.entities;

import claro.com.inspira.util.Base64Util;

public class Attach extends BasicAttach implements AttachInterface
{
    public String content;
    
    public void getOutStream() {
    }
    
    @Override
    public String getContent2() {
        return this.content;
    }
    
    @Override
    public Object getContent() throws Exception {
        if (this.getEncode() != null && this.getEncode().equals("BASE64")) {
            return Base64Util.decodeFileToBase64Binary(this.content);
        }
        if (this.getType() != null && this.getType().toUpperCase().equals("URL")) {
            return this.content;
        }
        return this.content.getBytes();
    }
    
    @Override
    public void setContent(final String content) {
        this.content = content;
    }
}
