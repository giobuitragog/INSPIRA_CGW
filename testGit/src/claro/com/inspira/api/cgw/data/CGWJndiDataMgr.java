// 

// 

package claro.com.inspira.api.cgw.data;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CGWJndiDataMgr extends CGWCommonDataMgr implements CGWDataMgrInterface
{
    DataSource ds;
    
    @Override
    public void connect() throws Exception {
        final Context initContext = new InitialContext();
        this.ds = (DataSource)initContext.lookup("jdbc/legacyAPIdevDS");
        this.connection = this.ds.getConnection();
    }
    
    public CGWJndiDataMgr() throws Exception {
        this.connect();
    }
}
