// 

// 

package claro.com.inspira.api.cgw.service;

import claro.com.inspira.util.Utilities;
import claro.com.inspira.api.cgw.data.CGWPersisterDataMgr;
import claro.com.inspira.api.cgw.data.CGWDataMgrInterface;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;

public class CGWCommonSender
{
    CGWMessageRequest request;
    CGWDataMgrInterface dataManager;
    
    public CGWCommonSender(final CGWDataMgrInterface dataManager) {
        this.request = new CGWMessageRequest();
        this.dataManager = null;
        this.dataManager = dataManager;
    }
    
    public void persistMessage() {
        try {
            final CGWPersisterDataMgr mgr = new CGWPersisterDataMgr();
            mgr.connect();
            this.request.setState("SENDED");
            this.request.setStateDate(new StringBuilder().append(Utilities.getMillisDate()).toString());
            mgr.putMessage(this.request);
            mgr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
