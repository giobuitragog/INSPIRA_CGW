// 

// 

package claro.com.inspira.api.cgw.sender;

import java.util.List;
import java.util.Iterator;
import claro.com.inspira.exception.CGWException;
import claro.com.inspira.util.ValidatorUtil;
import claro.com.inspira.api.cgw.entities.CGWBox;
import claro.com.inspira.api.cgw.entities.CGWChannel;
import java.util.Map;
import claro.com.inspira.api.cgw.sms.EriccsonSMSSender;

public class SMSSender extends CommonSender implements NotifyProtocolInterface
{
    EriccsonSMSSender sender;
    
    public SMSSender(final Map map) throws Exception {
        this.map = map;
        this.sender = new EriccsonSMSSender(map);
    }
    
    @Override
    public void validate() throws Exception {
        for (final CGWChannel channel : this.request.getMessageBox()) {
            if (channel.getMessageChannel().equals("SMS")) {
                final List<CGWBox> list = channel.getMessageBox();
                for (final CGWBox box : list) {
                    final String to = box.customerBox;
                    if (ValidatorUtil.validateNumeric(to) != null) {
                        throw new CGWException("El campo costumerBox para SMS debe ser n\u00famerico");
                    }
                }
            }
        }
    }
    
    @Override
    public String send() throws Exception {
        try {
            for (final CGWChannel channel : this.request.getMessageBox()) {
                if (channel.getMessageChannel().equals("SMS")) {
                    final List<CGWBox> list = channel.getMessageBox();
                    for (final CGWBox box : list) {
                        final String to = box.customerBox;
                        this.map.put("to", to);
                        this.map.put("text", this.getMessage());
                        this.sender.perform(this.map);
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
