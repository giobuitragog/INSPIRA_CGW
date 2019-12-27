// 

// 

package claro.com.inspira.api.cgw.sender;

import java.util.List;
import java.util.Iterator;
import claro.com.inspira.api.cgw.entities.CGWBox;
import claro.com.inspira.api.cgw.entities.CGWChannel;
import java.util.Map;
import claro.com.inspira.api.msexch.service.ExchangeMailer;

public class MailSender2 extends CommonSender implements NotifyProtocolInterface
{
    ExchangeMailer mailer;
    
    public MailSender2(final Map map) throws Exception {
        this.map = map;
        this.mailer = new ExchangeMailer(map);
    }
    
    @Override
    public void validate() throws Exception {
    }
    
    public void setProfileId() {
    }
    
    @Override
    public String send() throws Exception {
        try {
            if (this.request.getAttach() != null && this.request.getAttach().size() > 0) {
                this.map.put("attachments", this.request.getAttach());
            }
            for (final CGWChannel channel : this.request.getMessageBox()) {
                if (!channel.getMessageChannel().equals("SMS")) {
                    final List<CGWBox> list = channel.getMessageBox();
                    for (final CGWBox box : list) {
                        final String email = box.customerBox;
                        this.map.put("to", email);
                        this.map.put("text", this.getMessage());
                        if (this.request.getSubject() != null) {
                            this.map.put("subject", this.request.getSubject());
                        }
                        this.mailer.sendMail(this.map, true);
                    }
                }
            }
            return "";
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
