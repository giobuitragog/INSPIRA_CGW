// 

// 

package claro.com.inspira.util;

import claro.com.inspira.api.cgw.service.CGWService;

public class CGWTest
{
    public static String toRet;
    
    static {
        CGWTest.toRet = "";
    }
    
    public static void main(final String[] args) {
        try {
            final CGWService service = new CGWService();
            CGWTest.toRet = service.putMessage("{  \"customerId\": [      \"9F1AA44D-B90F-E811-80ED-FA163E10DFBE\"   ],   \"messageBox\": [      {         \"messageChannel\": \"SMTP\",         \"messageBox\": [            {\"customerBox\": \"jaime.buitrago@claro.com.co\"            }         ]      }   ],   \"profileId\": [      \"DIGITAL100\"   ],   \"communicationType\": [      \"REGULATORIO\"   ],   \"communicationOrigin\": \"DIGITAL100\",   \"deliveryReceipts\": \"NO\",   \"contentType\": \"CONTENTID\",   \"messageContent\": \"DIGITAL100_ClaroMail_PREPAGO\", \"variableMap\":{\"$CUENTA\": \"3132971144\", \"$CLIENTE\": \"GIOVANNY B\"},\"subject\": \"esto es una prueba INMEMORY \"}");
        }
        catch (Exception var2) {
            var2.printStackTrace();
            throw var2;
        }
    }
}
