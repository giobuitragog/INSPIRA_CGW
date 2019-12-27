// 

// 

package claro.com.inspira.api.cgw.data;

import claro.com.inspira.sqllite.CrudSQLLiteMain;
import javax.sql.DataSource;

public class CGWInMemoryDataMgr extends CGWCommonDataMgr implements CGWDataMgrInterface
{
    DataSource ds;
    public boolean isCreated;
    static CGWInMemoryDataMgr ref;
    CrudSQLLiteMain sqlLite;
    
    static {
        CGWInMemoryDataMgr.ref = null;
    }
    
    public static CGWInMemoryDataMgr getRef() throws Exception {
        if (CGWInMemoryDataMgr.ref == null) {
            CGWInMemoryDataMgr.ref = new CGWInMemoryDataMgr();
        }
        return CGWInMemoryDataMgr.ref;
    }
    
    @Override
    public void connect() throws Exception {
        if (!this.isCreated || this.sqlLite == null) {
            this.closeOnStatement = false;
            this.setSchema("");
            (this.sqlLite = new CrudSQLLiteMain()).openConnection();
            this.sqlLite.createDatabase();
            this.sqlLite.createTables();
            this.sqlLite.populateTables();
            this.isCreated = true;
        }
        this.setConnection(this.sqlLite.getC());
    }
    
    public CGWInMemoryDataMgr() throws Exception {
        this.isCreated = false;
        this.sqlLite = null;
        this.connect();
    }
}
