// 

// 

package claro.com.inspira.sqllite;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import claro.com.inspira.api.msexch.service.ExchangeMailer;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class TestInMemoryDB extends HttpServlet
{
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    public void doGet(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse) throws IOException, ServletException {
        try {
            final String toRet = ExchangeMailer.test();
            servletResponse.getOutputStream().println("current version 2!");
            servletResponse.getOutputStream().println(toRet);
        }
        catch (Exception e) {
            e.printStackTrace();
            servletResponse.getOutputStream().println(getString4Exception(e));
        }
    }
    
    public static String getString4Exception(final Exception e) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String sStackTrace = sw.toString();
        return sStackTrace;
    }
}
