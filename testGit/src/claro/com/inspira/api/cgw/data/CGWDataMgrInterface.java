// 

// 

package claro.com.inspira.api.cgw.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import claro.com.inspira.api.cgw.pojo.CGWMsgSchema;
import java.util.Hashtable;
import java.sql.SQLException;

public interface CGWDataMgrInterface
{
    void connect() throws Exception;
    
    String getValuesList(final String p0, final String p1) throws SQLException, Exception;
    
    String getTypeProfile(final String p0) throws Exception;
    
    Hashtable getProfile(final String p0) throws Exception;
    
    CGWMsgSchema getCGWMsgSchema(final String p0) throws Exception;
    
    Object getPojo(final PreparedStatement p0, final String p1) throws Exception;
    
    Connection getConnection();
    
    void setConnection(final Connection p0);
    
    void setSchema(final String p0);
}
