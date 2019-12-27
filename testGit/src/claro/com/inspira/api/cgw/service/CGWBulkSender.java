// 

// 

package claro.com.inspira.api.cgw.service;

import java.util.List;
import claro.com.inspira.api.cgw.sender.NotifyProtocolInterface;
import java.util.Hashtable;
import claro.com.inspira.util.Utilities;
import claro.com.inspira.api.cgw.entities.CGWBox;
import java.util.StringTokenizer;
import com.framework.util.UtilFile;
import claro.com.inspira.api.cgw.entities.CGWChannel;
import java.util.ArrayList;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;
import java.util.Map;
import claro.com.inspira.api.cgw.sender.SMSSender;
import claro.com.inspira.api.cgw.data.CGWDataMgrInterface;

public class CGWBulkSender extends CGWCommonSender
{
    public CGWBulkSender(final CGWDataMgrInterface dataManager) {
        super(dataManager);
    }
    
    public void perform(final String pathFile, final String profileId) throws Exception {
        final Hashtable map = this.dataManager.getProfile(profileId);
        final NotifyProtocolInterface sender = new SMSSender(map);
        (this.request = new CGWMessageRequest()).setPushType("SINGLE");
        this.request.setCommunicationOrigin("BSCS");
        final List<String> origins = new ArrayList<String>();
        origins.add("BSCS");
        this.request.setCommunicationType(origins);
        this.request.setDeliveryReceipts("NO");
        this.request.setContentType("MESSAGE");
        final List<String> profiles = new ArrayList<String>();
        profiles.add(profileId);
        this.request.setProfileId(profiles);
        final List<CGWChannel> listChannel = new ArrayList<CGWChannel>();
        final CGWChannel channel = new CGWChannel();
        channel.setMessageChannel("SMS");
        listChannel.add(channel);
        this.request.setMessageBox(listChannel);
        final String content = UtilFile.readFile(pathFile);
        final StringTokenizer token = new StringTokenizer(content, "\n");
        while (token.hasMoreTokens()) {
            final String line = token.nextToken();
            final StringTokenizer token2 = new StringTokenizer(line, "|");
            if (token2.countTokens() != 2) {
                final String message = token2.nextToken();
                final String customerId = token2.nextToken();
                final String msisdn = "3132971143";
                final List<CGWBox> listBox = new ArrayList<CGWBox>();
                final CGWBox box = new CGWBox();
                box.setCustomerId(customerId);
                box.setCustomerBox(msisdn);
                listBox.add(box);
                channel.setMessageBox(listBox);
                this.request.setMessageContent(message);
                this.request.setDateTime(Utilities.getMillisDate());
                sender.setRequest(this.request);
                sender.send();
                this.persistMessage();
            }
        }
    }
    
    public static void main(final String[] args) {
        final CGWBulkSender sender = new CGWBulkSender(null);
        try {
            sender.perform("D:/claro/", "SMS_FS_TCRM1");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
