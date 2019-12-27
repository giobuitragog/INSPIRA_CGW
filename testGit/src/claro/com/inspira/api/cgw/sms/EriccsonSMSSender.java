// 

// 

package claro.com.inspira.api.cgw.sms;

import java.util.HashMap;
import java.util.List;
import claro.com.inspira.api.cgw.ericsson.MapResponse;
import claro.com.inspira.api.cgw.ericsson.MapItem;
import java.util.ArrayList;
import java.util.Map;
import claro.com.inspira.api.cgw.ericsson.MapRequest;

public class EriccsonSMSSender
{
    NgwGeneralService ngwGeneralService;
    NgwGeneralPortType ngwGeneralPortType;
    MapRequest request;
    
    public EriccsonSMSSender(final Map map) throws Exception {
        this.request = new MapRequest();
        try {
            this.ngwGeneralService = new NgwGeneralService();
            this.ngwGeneralPortType = this.ngwGeneralService.getNgwGeneralPort();
            (this.request = new MapRequest()).setOrigin(map.get("origin"));
            this.request.setOperation(map.get("operation"));
            this.request.setOperation("INSTDC");
            this.request.setRequestID(0);
            this.request.setMsisdn(map.get("msisdn"));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public void perform(final Map map) {
        final List<MapRequest> list = new ArrayList<MapRequest>();
        final MapItem item = new MapItem();
        this.request.setMsisdn(map.get("to"));
        item.setKey("message");
        item.setValue(map.get("text"));
        final List<MapItem> listItems = new ArrayList<MapItem>();
        listItems.add(item);
        this.request.setItem(listItems);
        list.add(this.request);
        try {
            final List<MapResponse> response = this.ngwGeneralPortType.ngwRequest(list);
            if (response.size() == 0) {
                return;
            }
            final MapResponse responseItem = response.get(0);
            final int responseItemStrg = responseItem.getResponseID();
            final List<MapItem> responseList = responseItem.getResult();
            if (responseList.size() == 0) {
                return;
            }
            final MapItem responseItemList = responseList.get(0);
            final String key = responseItemList.getKey();
            final String value = responseItemList.getValue();
            System.out.println("La respuesta es : " + responseItemStrg + " - " + key + " :" + value);
        }
        catch (ServiceFailedExceptionException e) {
            final String message = e.getMessage();
            System.out.println(message);
        }
    }
    
    public static void main(final String[] args) {
        final Map map = new HashMap();
        map.put("msisdn", "573132971143");
        map.put("origin", "WS");
        map.put("operation", "COMM21");
        map.put("requestID", 0);
        map.put("msisdnA", "msisdnA");
        map.put("text", "ESTE ES UN MENSAJE DE PRUEBA INSPIRA");
        try {
            final EriccsonSMSSender sender = new EriccsonSMSSender(map);
            sender.perform(map);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
