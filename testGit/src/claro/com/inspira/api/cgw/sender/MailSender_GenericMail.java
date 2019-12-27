// 

// 

package claro.com.inspira.api.cgw.sender;

import claro.com.inspira.api.cgw.entities.CGWMessageRequest;
import java.util.Iterator;
import claro.com.inspira.api.cgw.entities.CGWChannel;
import claro.com.inspira.api.cgw.entities.CGWBox;
import java.util.List;
import java.util.Map;
import com.framework.util.GenericMail;

public class MailSender_GenericMail extends CommonSender implements NotifyProtocolInterface
{
    GenericMail mailer;
    
    @Override
    public void validate() throws Exception {
    }
    
    public MailSender_GenericMail(final Map map) {
        this.map = map;
        (this.mailer = new GenericMail()).mapBasicParams(this.mailer, map);
    }
    
    @Override
    public void setBoxes(final List<CGWBox> list) {
        this.list = list;
    }
    
    @Override
    public void setMessage(final Object message) {
        this.text = (String)message;
    }
    
    public void setProfileId() {
    }
    
    @Override
    public String send() throws Exception {
        try {
            for (final CGWChannel channel : this.request.getMessageBox()) {
                final List<CGWBox> list = channel.getMessageBox();
                for (final CGWBox box : list) {
                    final String email = box.customerBox;
                    this.mailer.setTo(email);
                    this.mailer.setText(this.getMessage());
                    this.mailer.sendAttach("");
                }
            }
            return "";
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public void persistLog() {
    }
    
    @Override
    public void setRequest(final CGWMessageRequest request) {
        this.request = request;
    }
}
