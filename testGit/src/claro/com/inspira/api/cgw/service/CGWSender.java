// 

// 

package claro.com.inspira.api.cgw.service;

import claro.com.inspira.api.cgw.data.Constans;
import claro.com.inspira.api.cgw.data.CGWPersisterDataMgr;
import claro.com.inspira.util.Utilities;
import java.util.Hashtable;
import claro.com.inspira.exception.CGWException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import claro.com.inspira.api.cgw.sender.MailSender;
import java.util.Map;
import claro.com.inspira.api.cgw.sender.SMSSender;
import claro.com.inspira.api.cgw.data.CGWDataMgrInterface;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;
import claro.com.inspira.api.cgw.pojo.CGWMsgSchema;
import claro.com.inspira.api.cgw.sender.NotifyProtocolInterface;
import claro.com.inspira.api.cgw.entities.CGWChannel;
import java.util.List;

public class CGWSender extends CGWCommonSender implements Runnable
{
    String profileId;
    List<CGWChannel> boxes;
    NotifyProtocolInterface sender;
    CGWMsgSchema schema;
    
    public CGWSender(final String profileId, final CGWMessageRequest request, final CGWDataMgrInterface dataManager, final CGWMsgSchema schema) {
        super(dataManager);
        this.profileId = profileId;
        this.boxes = request.getMessageBox();
        this.request = request;
        this.schema = schema;
    }
    
    public void getSender() throws Exception {
        try {
            final String type = this.dataManager.getTypeProfile(this.profileId);
            final Hashtable map = this.dataManager.getProfile(this.profileId);
            if (type.equals("SMS") || type == null) {
                this.sender = new SMSSender(map);
            }
            else if (type.equals("MAIL")) {
                this.sender = new MailSender(map);
            }
            this.sender.setRequest(this.request);
            this.sender.setSchema(this.schema);
            this.sender.validate();
        }
        catch (Exception e) {
            e.printStackTrace();
            final StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new CGWException("Error: no se encontro el profileID :'" + this.profileId + "' : " + errors);
        }
    }
    
    public void setLog() {
    }
    
    public String sincronousRun() throws Exception {
        try {
            final String ret = this.sender.send();
            this.persistMessage();
            return ret;
        }
        catch (Exception e) {
            e.printStackTrace();
            final String error = Utilities.getString4Exception(e);
            return error;
        }
    }
    
    @Override
    public void run() {
        try {
            this.sender.send();
            this.persistMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void persistMessage() {
        try {
            final CGWPersisterDataMgr mgr = new CGWPersisterDataMgr();
            mgr.connect();
            this.request.setState("SENDED");
            this.request.setStateDate(new StringBuilder().append(Utilities.getMillisDate()).toString());
            if (Constans.logMessages) {
                mgr.putMessage(this.request);
            }
            mgr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
