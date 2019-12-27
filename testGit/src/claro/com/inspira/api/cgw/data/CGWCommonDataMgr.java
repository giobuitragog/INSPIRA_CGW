// 

// 

package claro.com.inspira.api.cgw.data;

import claro.com.inspira.exception.ServiceApiException;
import claro.com.inspira.util.Utilities;
import com.framework.bean.BeanConverter2;
import claro.com.inspira.api.cgw.pojo.CGWMsgSchema;
import com.framework.data.impl.MapResultSetToObject;
import java.util.Hashtable;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class CGWCommonDataMgr
{
    public Connection connection;
    String schema;
    public boolean closeOnStatement;
    
    public CGWCommonDataMgr() {
        this.schema = "API_UTIL_INSP.";
        this.closeOnStatement = true;
    }
    
    public void connect() throws Exception {
    }
    
    public String getValuesList(final String mappingId, final String expName) throws SQLException, Exception {
        if (this.connection.isClosed()) {
            this.connect();
        }
        final PreparedStatement st = this.connection.prepareStatement("SELECT MAX(VALUE) from " + this.schema + "META_MAPPING_EXPR where MAPPINGNAME=? and EXPRESION_NAME=?");
        st.setString(1, mappingId);
        st.setString(2, expName);
        final ResultSet rs = st.executeQuery();
        if (rs.next()) {
            final String type = rs.getString(1);
            st.close();
            rs.close();
            if (this.closeOnStatement) {
                this.connection.close();
            }
            return type;
        }
        return null;
    }
    
    public String getTypeProfile(final String profile) throws Exception {
        if (this.connection.isClosed()) {
            this.connect();
        }
        final PreparedStatement st = this.connection.prepareStatement("SELECT MAX(CGW_TYPE) as CGW_TYPE from " + this.schema + "META_MAIL_PROFILE where profileID=?");
        st.setString(1, profile);
        final ResultSet rs = st.executeQuery();
        if (rs.next()) {
            final String type = rs.getString(1);
            st.close();
            rs.close();
            if (this.closeOnStatement) {
                this.connection.close();
            }
            return type;
        }
        return null;
    }
    
    public Hashtable getProfile(final String profile) throws Exception {
        if (this.connection.isClosed()) {
            this.connect();
        }
        final PreparedStatement st = this.connection.prepareStatement("select feature, value from " + this.schema + "meta_mail_profile where profileID=?");
        st.setString(1, profile);
        final ResultSet rs = st.executeQuery();
        final Hashtable table = MapResultSetToObject.getHashTableLabelValue(rs);
        st.close();
        rs.close();
        if (this.closeOnStatement) {
            this.connection.close();
        }
        return table;
    }
    
    public CGWMsgSchema getCGWMsgSchema(final String contentId) throws Exception {
        final String sql = "select * from " + this.schema + "CGW_META_MSGSCHEMA WHERE CONTENTID=?";
        this.connect();
        final PreparedStatement st = this.connection.prepareStatement(sql);
        st.setString(1, contentId);
        return (CGWMsgSchema)this.getPojo(st, "claro.com.inspira.api.cgw.pojo.CGWMsgSchema");
    }
    
    public Object getPojo(final PreparedStatement st, final String className) throws Exception {
        try {
            this.connect();
            final ResultSet rs = st.executeQuery();
            rs.next();
            final BeanConverter2 bean = new BeanConverter2(className, rs.getMetaData());
            final Object toReturn = bean.getBean(rs);
            rs.close();
            st.close();
            if (this.closeOnStatement) {
                this.connection.close();
            }
            return toReturn;
        }
        catch (Exception e) {
            throw new ServiceApiException("Error al recuperar el registro: " + Utilities.getString4Exception(e));
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
    
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
    
    public String getSchema() {
        return this.schema;
    }
    
    public void setSchema(final String schema) {
        this.schema = schema;
    }
}
