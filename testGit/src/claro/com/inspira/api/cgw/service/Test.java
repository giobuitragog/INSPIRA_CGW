// 

// 

package claro.com.inspira.api.cgw.service;

import com.framework.util.UtilFile;

public class Test
{
    public static void main(final String[] args) {
        final CGWService service = new CGWService();
        try {
            final String message = UtilFile.readFile("C:/claro/inspira/apis_claro/documentacion/test/GET_MESSAGE_REQUEST.txt");
            System.out.println(service.getMessage(message));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
