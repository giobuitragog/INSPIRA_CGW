// 

// 

package claro.com.inspira.api.cgw.data;

import com.framework.data.DatabaseInstance;
import com.framework.dao.objects.DatabaseMappings;
import javax.sql.DataSource;
import java.sql.Connection;

public class CGWJdbcDataMgr extends CGWCommonDataMgr implements CGWDataMgrInterface
{
    static CGWJdbcDataMgr ref;
    Connection connection;
    DataSource ds;
    
    static {
        CGWJdbcDataMgr.ref = null;
    }
    
    public CGWJdbcDataMgr() throws Exception {
        final DatabaseMappings mappings = new DatabaseMappings("bridge_");
        final DatabaseInstance db = new DatabaseInstance(mappings);
    }
    
    public static CGWJdbcDataMgr getRef() throws Exception {
        if (CGWJdbcDataMgr.ref == null) {
            CGWJdbcDataMgr.ref = new CGWJdbcDataMgr();
        }
        return CGWJdbcDataMgr.ref;
    }
}
