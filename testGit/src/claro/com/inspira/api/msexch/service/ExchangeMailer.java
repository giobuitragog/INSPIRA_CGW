// 

// 

package claro.com.inspira.api.msexch.service;

import java.util.Date;
import java.util.HashMap;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import claro.com.inspira.exception.CGWException;
import claro.com.inspira.util.Utilities;
import com.framework.util.UtilFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.CopyOption;
import java.nio.file.Paths;
import claro.com.inspira.util.Constans;
import java.net.URL;
import claro.com.inspira.api.common.entities.Attach;
import java.util.ArrayList;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import claro.com.inspira.api.cgw.sender.CertifierManager4;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import java.net.URI;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import java.util.Map;
import microsoft.exchange.webservices.data.core.ExchangeService;

public class ExchangeMailer
{
    ExchangeService service;
    
    public ExchangeMailer(final Map map) throws Exception {
        this.service = new ExchangeService();
        final ExchangeCredentials credentials = (ExchangeCredentials)new WebCredentials((String)map.get("mailUser"), (String)map.get("mailPassword"));
        this.service.setTraceEnabled(true);
        final String host = (String)map.get("SmtpHost");
        final URI uri = new URI((String)map.get("SmtpHost"));
        this.service.setCredentials(credentials);
        this.service.setUrl(uri);
        this.service.validate();
    }
    
    public String sendMail(final Map map, final boolean... setTrust) throws Exception {
        String ret = "";
        try {
            final CertifierManager4 mgr = new CertifierManager4();
            mgr.loadCert1();
            final EmailMessage msg = new EmailMessage(this.service);
            if (map.get("subject") != null) {
                msg.setSubject((String)map.get("subject"));
            }
            msg.setBody(MessageBody.getMessageBodyFromText((String)map.get("text")));
            msg.getToRecipients().add((String)map.get("to"));
            if (map.get("attachments") != null) {
                final List<Attach> list = (List) map.get("attachments");
                for (final Attach attach : list) {
                    if (attach.getEncode() != null && attach.getEncode().equals("BASE64")) {
                        final byte[] contentFile = (byte[])attach.getContent();
                        msg.getAttachments().addFileAttachment(attach.getName(), contentFile);
                    }
                    else {
                        if (attach.getType() != null && attach.getType().toUpperCase().equals("URL")) {
                            try {
                                final InputStream in = new URL((String)attach.getContent()).openStream();
                                Files.copy(in, Paths.get(String.valueOf(Constans._PATHTOTMPFILES) + "tmp", new String[0]), StandardCopyOption.REPLACE_EXISTING);
                                final String contentFile2 = UtilFile.readFile(String.valueOf(Constans._PATHTOTMPFILES) + "tmp");
                                msg.getAttachments().addFileAttachment(attach.getName(), contentFile2.getBytes());
                                continue;
                            }
                            catch (Exception e) {
                                final String message = Utilities.getString4Exception(e);
                                e.printStackTrace();
                                throw new CGWException("Error al adjuntar el archivo, compruebe que la url sea correcta y accesible");
                            }
                        }
                        final String contentFile3 = (String)attach.getContent();
                        msg.getAttachments().addFileAttachment(attach.getName(), contentFile3.getBytes());
                    }
                }
            }
            msg.send();
            ret = String.valueOf(ret) + "envio correcto: " + map.get("text") + "\n";
            return ret;
        }
        catch (Exception e2) {
            final String message2 = ret = Utilities.getString4Exception(e2);
            throw new CGWException(String.valueOf(ret) + "\n" + message2);
        }
    }
    
    public static String test() throws Exception {
        final Map map = new HashMap();
        map.put("mailUser", "jaime.buitrago@claro.com.co");
        map.put("mailUncualified", "jaime.buitrago");
        map.put("mailPassword", "Bogota2019*");
        map.put("SmtpHost", "https://outlook.office365.com/EWS/Exchange.asmx");
        map.put("text", "this is a test " + new Date());
        map.put("to", "jaime.buitrago@claro.com.co");
        final List<Attach> attachs = new ArrayList<Attach>();
        map.put("attachments", attachs);
        final ExchangeMailer mail = new ExchangeMailer(map);
        String ret = mail.sendMail(map, true);
        ret = String.valueOf(ret) + "email sended";
        return ret;
    }
    
    public static void main(final String[] args) throws Exception {
        final Map map = new HashMap();
        System.out.println(test());
    }
}
