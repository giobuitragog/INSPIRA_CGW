// 

// 

package claro.com.inspira.api.cgw.data;

import org.mongojack.DBCursor;
import claro.com.inspira.api.cgw.entities.CGWMessageDaoNA;
import claro.com.inspira.api.cgw.entities.CGWMessageDaoA;
import java.util.List;
import claro.com.inspira.api.cgw.entities.CGWMessageGetRequest;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;
import java.util.Map;
import java.util.HashMap;
import claro.com.inspira.data.MongoDbUtilityMgr;
import javax.sql.DataSource;
import java.sql.Connection;

public class CGWPersisterDataMgr
{
    static CGWJndiDataMgr ref;
    Connection connection;
    DataSource ds;
    MongoDbUtilityMgr mgr;
    
    static {
        CGWPersisterDataMgr.ref = null;
    }
    
    public CGWPersisterDataMgr() {
        this.mgr = new MongoDbUtilityMgr();
    }
    
    public void connect() throws Exception {
        final Map map = new HashMap();
        map.put("dbHost", "100.126.19.77");
        map.put("dbPort", 27017);
        map.put("dbName", "cgw_collections");
        map.put("dbCollectionName", "cgw_messages");
        this.mgr.connect(map);
    }
    
    public void close() {
        this.mgr.close();
    }
    
    public void putMessage(final CGWMessageRequest request) {
        this.mgr.write(request, CGWMessageRequest.class);
    }
    
    public List getMessage(final CGWMessageGetRequest request) {
        DBCursor<Object> cursor = null;
        if (request.getGetAttachContent()) {
            cursor = this.mgr.read(CGWMessageDaoA.class, request);
        }
        else {
            cursor = this.mgr.read(CGWMessageDaoNA.class, request);
        }
        if (cursor.count() > 0) {
            final List rows = cursor.toArray();
            return rows;
        }
        return null;
    }
}
