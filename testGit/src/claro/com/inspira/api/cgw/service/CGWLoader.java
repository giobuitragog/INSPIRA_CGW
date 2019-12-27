// 

// 

package claro.com.inspira.api.cgw.service;

import claro.com.inspira.api.cgw.entities.CGWMessageDaoNA;
import claro.com.inspira.api.cgw.entities.CGWMessageGetResponseNA;
import claro.com.inspira.api.cgw.entities.CGWMessageDao;
import java.util.List;
import claro.com.inspira.api.cgw.entities.CGWMessageGetResponse;
import claro.com.inspira.api.cgw.data.CGWPersisterDataMgr;
import com.framework.util.DateUtil;
import claro.com.inspira.api.cgw.entities.CGWMessageGetRequest;

public class CGWLoader
{
    public Object getMessages(final CGWMessageGetRequest request) throws Exception {
        final Long init = DateUtil.getMillisDate();
        final CGWPersisterDataMgr mgr = new CGWPersisterDataMgr();
        mgr.connect();
        final List messages = mgr.getMessage(request);
        if (request.getGetAttachContent()) {
            final CGWMessageGetResponse responseObject = new CGWMessageGetResponse();
            responseObject.setMessages(messages);
            final Long end = DateUtil.getMillisDate();
            responseObject.setQueryTime(end - init);
            mgr.close();
            return responseObject;
        }
        final CGWMessageGetResponseNA responseObject2 = new CGWMessageGetResponseNA();
        responseObject2.setMessages(messages);
        mgr.close();
        final Long end = DateUtil.getMillisDate();
        responseObject2.setQueryTime(end - init);
        mgr.close();
        return responseObject2;
    }
}
