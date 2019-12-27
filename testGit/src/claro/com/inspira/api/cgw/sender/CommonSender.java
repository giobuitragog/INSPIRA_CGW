// 

// 

package claro.com.inspira.api.cgw.sender;

import com.framework.util.UtilString;
import java.util.StringTokenizer;
import claro.com.inspira.api.cgw.pojo.CGWMsgSchema;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;
import java.util.Map;
import claro.com.inspira.api.cgw.entities.CGWBox;
import java.util.List;

public class CommonSender
{
    String text;
    List<CGWBox> list;
    Map map;
    CGWMessageRequest request;
    CGWMsgSchema schema;
    
    public void setBoxes(final List<CGWBox> list) {
        this.list = list;
    }
    
    public CommonSender() {
        this.map = null;
        this.schema = null;
    }
    
    public void setMessage(final Object message) {
        this.text = (String)message;
    }
    
    public void setRequest(final CGWMessageRequest request) {
        this.request = request;
    }
    
    public String getMessage() throws Exception {
        if (!this.request.getContentType().toUpperCase().equals("CONTENTID")) {
            return this.request.getMessageContent();
        }
        if (!this.schema.getTYPE_SCH().equals("RESOURCE")) {
            return this.fillVariables(this.schema.getVariablesSet(), this.request.getVariableMap(), this.schema.getContent());
        }
        return this.fillVariables(this.schema.getVariablesSet(), this.request.getVariableMap(), this.schema.getResourceContent());
    }
    
    public String fillVariables(final String variablesMap, final Map values, final String originalMsg) {
        final StringTokenizer token = new StringTokenizer(variablesMap, "|");
        String message = originalMsg;
        while (token.hasMoreTokens()) {
            final String nextVar = token.nextToken();
            final String nextVal = (values.get(nextVar) == null) ? "" : (String)values.get(nextVar);
            message = (nextVal.equals("") ? message : UtilString.replace(message, nextVar, nextVal));
        }
        return message;
    }
    
    public void persistLog() {
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public CGWMsgSchema getSchema() {
        return this.schema;
    }
    
    public void setSchema(final CGWMsgSchema schema) {
        this.schema = schema;
    }
}
