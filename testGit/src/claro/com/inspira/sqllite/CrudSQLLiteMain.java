// 

// 

package claro.com.inspira.sqllite;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import com.framework.data.impl.MapResultSetToObject;
import java.util.Hashtable;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;

public class CrudSQLLiteMain
{
    Connection c;
    Statement stmt;
    String url;
    String driver;
    
    public CrudSQLLiteMain() {
        this.c = null;
        this.stmt = null;
        this.url = "jdbc:sqlite::memory:";
        this.driver = "org.sqlite.JDBC";
    }
    
    public void createDatabase() {
        Connection c = null;
        try {
            Class.forName(this.driver);
            c = DriverManager.getConnection(this.url);
        }
        catch (Exception e) {
            System.err.println(String.valueOf(e.getClass().getName()) + ": " + e.getMessage());
        }
        System.out.println("database successfully created");
    }
    
    public void openConnection() throws Exception {
        if (this.c != null) {
            if (!this.c.isClosed()) {
                this.stmt = this.c.createStatement();
                return;
            }
        }
        try {
            Class.forName(this.driver);
            this.c = DriverManager.getConnection(this.url);
            System.out.println("Database Opened...\n");
            this.stmt = this.c.createStatement();
        }
        catch (Exception e) {
            System.err.println(String.valueOf(e.getClass().getName()) + ": " + e.getMessage());
        }
    }
    
    public void closeConnection() {
        try {
            this.c.close();
            this.stmt.close();
        }
        catch (Exception e) {
            System.err.println(String.valueOf(e.getClass().getName()) + ": " + e.getMessage());
        }
    }
    
    public void executeUpdate(final String sql) {
        try {
            System.out.println("Database Opened...\n");
            this.stmt.executeUpdate(sql);
        }
        catch (Exception e) {
            System.err.println(String.valueOf(e.getClass().getName()) + ": " + e.getMessage());
        }
        System.out.println("Table Product Successfully!!!");
    }
    
    public void insert(final String sql) {
        try {
            this.stmt.executeUpdate(sql);
        }
        catch (Exception e) {
            System.err.println("Error on: " + sql);
            System.err.println(String.valueOf(e.getClass().getName()) + ": " + e.getMessage());
        }
    }
    
    public Hashtable select(final String query) {
        try {
            final ResultSet rs = this.stmt.executeQuery(query);
            final Hashtable table = MapResultSetToObject.getHashTableLabelValue(rs);
            rs.close();
            return table;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void createTables() {
        final String sql = "CREATE TABLE META_MAPPING_EXPR ( EXPRESION_NAME TEXT, VALUE TEXT, ID INTEGER, MAPPINGNAME TEXT\t);";
        final String sql2 = "CREATE TABLE META_MAIL_PROFILE ( FEATURE TEXT, VALUE TEXT, PROFILEID TEXT, CGW_TYPE TEXT\t)";
        final String sql3 = "CREATE TABLE CGW_META_MSGSCHEMA ( CONTENTID TEXT, CONTENTNAME TEXT, CONTENT TEXT, VARIABLESSET TEXT, CGWPROFILE TEXT, TYPE_SCH\tTEXT)";
        this.executeUpdate(sql);
        this.executeUpdate(sql2);
        this.executeUpdate(sql3);
    }
    
    public void populateTables() {
        final List<String> inserts = new ArrayList<String>();
        inserts.add("INSERT INTO CGW_META_MSGSCHEMA (CONTENTID,CONTENTNAME,CONTENT,VARIABLESSET,CGWPROFILE, TYPE_SCH) VALUES  ( 'DIGITAL100_ClaroMail_PREPAGO' ,'DIGITAL100_ClaroMail_PREPAGO' ,'resources/ClaroMail_PREPAGO.html' ,'$CUENTA|$CLIENTE','SMTP','RESOURCE')");
        inserts.add("INSERT INTO META_MAPPING_EXPR (EXPRESION_NAME,VALUE,ID,MAPPINGNAME) VALUES ('communication_origin','TCRM|PLM|CLM|EOC|XCOLLECTIONS|BCSC|NMS|CMS|FS_BSCS|DIGITAL100',1,'CGW')");
        inserts.add("INSERT INTO META_MAPPING_EXPR (EXPRESION_NAME,VALUE,ID,MAPPINGNAME) VALUES ('content_types','MESSAGE|CONTENTID',1,'CGW')");
        inserts.add("INSERT INTO CGW_META_MSGSCHEMA (CONTENTID,CONTENTNAME,CONTENT,VARIABLESSET,CGWPROFILE) VALUES ('NOT_PAGO 01','NOT_PAGO 01','El pago de tu servicio Claro , Referencia  $REFERENCIA_DE_PAGO  por  valor de $VALOR_PAGADO fue exitoso. Sigue disfrutando los beneficios que tienes por ser parte de la familia Claro.','$REFERENCIA_DE_PAGO|$VALOR_PAGADO','SMS|SMTP')");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'origin','WS','DIGITAL100','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'operation','CFAF25','DIGITAL100','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'requestID','0','DIGITAL100','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'msisdnA','msisdnA','DIGITAL100','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'SmtpHost','https://owa.comcel.com.co/EWS/Exchange.asmx', 'DIGITAL100','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailPassword','Control2018*','DIGITAL100','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailUser','DMTVCMCO','DIGITAL100','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailUncualified','DMTVCMCO','DIGITAL100','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'origin','WS','PININSPIRA','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'operation','CFAF25','PININSPIRA','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'requestID','0','PININSPIRA','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'msisdnA','msisdnA','PININSPIRA','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'SmtpHost','https://owa.comcel.com.co/EWS/Exchange.asmx', 'PININSPIRA','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailPassword','Cl@r0Insp2*','PININSPIRA','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'origin','WS','SMTP_FS_TCRM1','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'operation','CFAF25','SMTP_FS_TCRM1','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'requestID','0','SMTP_FS_TCRM1','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'msisdnA','msisdnA','SMTP_FS_TCRM1','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'SmtpHost','https://outlook.office365.com/EWS/Exchange.asmx','SMTP_FS_TCRM1','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailPassword','Claro2020*','SMTP_FS_TCRM1','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailUser','jaime.buitrago@claro.com.co','SMTP_FS_TCRM1','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailUncualified','jaime.buitrago','SMTP_FS_TCRM1','MAIL' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'origin','WS','SMS_FS_TCRM1','SMS' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'requestID','0','SMS_FS_TCRM1','SMS' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailPassword','Claro2020*','SMS_FS_TCRM1','SMS' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailUncualified','jaime.buitrago','SMS_FS_TCRM1','SMS' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'operation','CFAF25','SMS_FS_TCRM1','SMS' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'msisdnA','msisdnA','SMS_FS_TCRM1','SMS' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'SmtpHost','https://outlook.office365.com/EWS/Exchange.asmx','SMS_FS_TCRM1','SMS' )");
        inserts.add("INSERT INTO META_MAIL_PROFILE ('FEATURE','VALUE','PROFILEID','CGW_TYPE') VALUES( 'mailUser','jaime.buitrago@claro.com.co','SMS_FS_TCRM1','SMS' )");
        for (final String command : inserts) {
            this.insert(command);
        }
        final Hashtable table = this.select("select * from CGW_META_MSGSCHEMA where CONTENTID='DIGITAL100_ClaroMail_PREPAGO'");
        System.out.println(table.toString());
        System.out.println("insert rows Successfully!!!");
    }
    
    public static void main(final String[] args) {
        try {
            final CrudSQLLiteMain test = new CrudSQLLiteMain();
            test.createDatabase();
            test.openConnection();
            test.createTables();
            test.populateTables();
            test.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getC() {
        return this.c;
    }
    
    public void setC(final Connection c) {
        this.c = c;
    }
    
    public Statement getStmt() {
        return this.stmt;
    }
    
    public void setStmt(final Statement stmt) {
        this.stmt = stmt;
    }
}
