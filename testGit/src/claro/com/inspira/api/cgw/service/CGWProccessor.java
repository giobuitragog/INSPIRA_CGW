// 

// 

package claro.com.inspira.api.cgw.service;

import com.framework.util.DateUtil;
import java.util.Iterator;
import java.util.List;
import claro.com.inspira.api.cgw.entities.CGWMessageResponse;
import claro.com.inspira.api.cgw.entities.JsonParser;
import java.util.Vector;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;
import java.sql.SQLException;
import claro.com.inspira.exception.CGWException;
import claro.com.inspira.api.cgw.data.CGWJdbcDataMgr;
import claro.com.inspira.api.cgw.data.CGWInMemoryDataMgr;
import claro.com.inspira.api.cgw.data.CGWJndiDataMgr;
import claro.com.inspira.api.cgw.data.Constans;
import claro.com.inspira.api.cgw.pojo.CGWMsgSchema;
import claro.com.inspira.api.cgw.data.CGWDataMgrInterface;
import claro.com.inspira.json.util.JsonManager;

public class CGWProccessor extends JsonManager
{
    CGWDataMgrInterface dataManager;
    CGWMsgSchema schema;
    public boolean isValid;
    public String validationMessage;
    
    public CGWProccessor(final String request, final Class objectClass) throws Exception {
        super(request, objectClass);
        this.dataManager = null;
        this.schema = null;
        this.isValid = false;
        this.validationMessage = "";
        this.dataManager = null;
        if (Constans.DATA_MANAGER.equals("JNDI")) {
            this.dataManager = new CGWJndiDataMgr();
        }
        else if (Constans.DATA_MANAGER.equals("INMEMORY")) {
            this.dataManager = new CGWInMemoryDataMgr();
        }
        else {
            this.dataManager = CGWJdbcDataMgr.getRef();
        }
    }
    
    public void validatePushType() throws Exception {
    }
    
    public boolean searchInValues(final String mappingId, final String expName, final String value) throws SQLException, Exception {
        final String values = this.dataManager.getValuesList(mappingId, expName);
        if (values.toUpperCase().indexOf(value.toUpperCase()) <= -1) {
            throw new CGWException("No se ha encontrado un valor v\u00e1lido '" + value + "' para " + expName);
        }
        return true;
    }
    
    public void validateOrigin() throws Exception {
        final CGWMessageRequest request = (CGWMessageRequest)this.getToMap();
        final String value = request.getCommunicationOrigin();
        this.searchInValues("CGW", "communication_origin", value);
    }
    
    public void validateMessageBoxes() throws Exception {
    }
    
    public void validateMessageContent() throws Exception {
        final CGWMessageRequest request = (CGWMessageRequest)this.getToMap();
        final String value = request.getContentType();
        this.searchInValues("CGW", "content_types", value);
        if (request.getContentType().toUpperCase().equals("CONTENTID")) {
            this.schema = this.getMessageSchema(request);
            final String text = this.getMessage(request, this.schema);
            this.schema.setResourceContent(text);
        }
    }
    
    public CGWMsgSchema getMessageSchema(final CGWMessageRequest request) throws Exception {
        CGWMsgSchema msgSchema = null;
        msgSchema = this.dataManager.getCGWMsgSchema(request.getMessageContent());
        return msgSchema;
    }
    
    public String getMessage(final CGWMessageRequest request, final CGWMsgSchema msgSchema) throws Exception {
        if (!request.getContentType().toUpperCase().equals("CONTENTID")) {
            return request.getMessageContent();
        }
        if (!msgSchema.getTYPE_SCH().equals("RESOURCE")) {
            return request.getMessageContent();
        }
        final InputStream stream = this.getClass().getResourceAsStream(msgSchema.getContent());
        final String content = this.readFromInputStream(stream);
        return content;
    }
    
    private String readFromInputStream(final InputStream inputStream) throws IOException {
        final StringBuilder resultStringBuilder = new StringBuilder();
        Throwable t = null;
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
            finally {
                if (br != null) {
                    br.close();
                }
            }
        }
        finally {
            if (t == null) {
                final Throwable exception=null;
                t = exception;
            }
            else {
                final Throwable exception=null;
                if (t != exception) {
                    t.addSuppressed(exception);
                }
            }
        }
        return resultStringBuilder.toString();
    }
    
    public boolean validateRequest() throws Exception {
        try {
            this.validateNotNull();
            this.validatePushType();
            this.validateOrigin();
            this.validateMessageBoxes();
            this.validateMessageContent();
        }
        catch (Exception e) {
            this.appendMessage("Error: En  la validaci\u00f3n de la estructura del mensaje: " + e.getMessage());
            throw e;
        }
        return this.isValid = true;
    }
    
    public void validateNotNull() throws CGWException, Exception {
        final Vector<String> notNullFields = new Vector<String>();
        notNullFields.add("pushType");
        notNullFields.add("typeCostumer");
        notNullFields.add("messageBox");
        notNullFields.add("profileId");
        notNullFields.add("communicationType");
        notNullFields.add("communicationOrigin");
        notNullFields.add("deliveryReceipts");
        notNullFields.add("contentType");
        notNullFields.add("messageContent");
        final JsonParser parser = new JsonParser();
        final CGWMessageRequest request = (CGWMessageRequest)this.getToMap();
        parser.validateNotNull(notNullFields, request, request.getClass().getName());
        if (parser.getNullsFinded().size() > 0) {
            throw new CGWException("Los siguientes campos no deben ser nulos: '" + parser.getNumericsFinded());
        }
    }
    
    public void proccess2() throws Exception {
        try {
            if (!this.isValid) {
                final CGWMessageResponse response = new CGWMessageResponse();
                response.setIsValid(false);
                response.setMessage((Object)("Error: " + this.getMessage().toString()));
                return;
            }
            final CGWMessageRequest request = (CGWMessageRequest)this.getToMap();
            final List<String> profilesIds = request.getProfileId();
            for (final String profileId : profilesIds) {
                final CGWSender sender = new CGWSender(profileId, request, this.dataManager, this.schema);
                sender.getSender();
                final Thread threadSender = new Thread(sender);
                threadSender.start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }
    
    public String proccess(final boolean isSincronous) throws Exception {
        String ret = "";
        try {
            if (!this.isValid) {
                final CGWMessageResponse response = new CGWMessageResponse();
                response.setIsValid(false);
                response.setMessage((Object)("Error: " + this.getMessage().toString()));
                return "";
            }
            final CGWMessageRequest request = (CGWMessageRequest)this.getToMap();
            if (request.getIdMessage() == null) {
                request.setIdMessage(new StringBuilder().append(DateUtil.getMillisDate()).toString());
            }
            final List<String> profilesIds = request.getProfileId();
            for (final String profileId : profilesIds) {
                final CGWSender sender = new CGWSender(profileId, request, this.dataManager, this.schema);
                try {
                    sender.getSender();
                    if (isSincronous) {
                        final Thread threadSender = new Thread(sender);
                        threadSender.start();
                    }
                    else {
                        ret = String.valueOf(ret) + sender.sincronousRun();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("Error: " + e2.getMessage());
            throw e2;
        }
        return ret;
    }
    
    public boolean isValid() {
        return this.isValid;
    }
    
    public void setValid(final boolean isValid) {
        this.isValid = isValid;
    }
    
    public String getValidationMessage() {
        return this.validationMessage;
    }
    
    public void setValidationMessage(final String validationMessage) {
        this.validationMessage = validationMessage;
    }
}
