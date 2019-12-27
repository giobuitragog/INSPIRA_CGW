// 

// 

package claro.com.inspira.api.cgw.service;

import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import claro.com.inspira.api.cgw.entities.GetPinResponse;
import claro.com.inspira.api.cgw.entities.GetPinRequest;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonParseException;
import claro.com.inspira.api.cgw.entities.GetUrlResponse;
import claro.com.inspira.api.cgw.entities.GetUrlRequest;
import javax.ws.rs.PUT;
import com.fasterxml.jackson.core.JsonProcessingException;
import claro.com.inspira.util.Utilities;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import com.fasterxml.jackson.databind.ObjectMapper;
import claro.com.inspira.api.cgw.entities.CGWMessageGetResponse;
import claro.com.inspira.json.util.JsonManager;
import claro.com.inspira.api.cgw.entities.CGWMessageGetResponseNA;
import claro.com.inspira.api.cgw.entities.CGWMessageGetRequest;
import claro.com.inspira.api.cgw.entities.CGWMessageResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import claro.com.inspira.common.services.GenericService;

@Path("CGWApi")
public class CGWService extends GenericService
{
    public CGWService() {
        this.methods.put("GET", "getUrl|getPin");
        this.methods.put("POST", "getUrl|getPin|putMessage");
    }
    
    @GET
    @Produces({ "application/json" })
    @Path("getMessage/{message}")
    public String getMessage(@PathParam("message") final String message) throws Exception {
        final CGWMessageResponse response = new CGWMessageResponse();
        try {
            final GenericService parser = new GenericService();
            parser.setObjectClass((Class)CGWMessageGetRequest.class);
            parser.getInstance(message);
            response.setIsValid(true);
            final CGWLoader loader = new CGWLoader();
            final CGWMessageGetRequest request = (CGWMessageGetRequest)parser.getRequest();
            final Object responseObject = loader.getMessages(request);
            String jsonMessage = null;
            if (request.getGetAttachContent()) {
                jsonMessage = JsonManager.convertObjectToJson((Class)CGWMessageGetResponseNA.class, responseObject);
            }
            else {
                jsonMessage = JsonManager.convertObjectToJson((Class)CGWMessageGetResponse.class, responseObject);
            }
            response.setMessage((Object)jsonMessage);
            return JsonManager.convertObjectToJson((Class)CGWMessageResponse.class, (Object)response);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setIsValid(false);
            response.setMessage((Object)("Error: excepcion en el procesamiento: " + e));
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithView((Class)CGWMessageResponse.class).writeValueAsString((Object)response);
        }
    }
    
    @PUT
    @Produces({ "application/json" })
    @Path("putMessage/{message}")
    public String putMessage(@PathParam("message") final String message) {
        final CGWMessageResponse response = new CGWMessageResponse();
        try {
            final CGWProccessor processor = new CGWProccessor(message, CGWMessageRequest.class);
            if (!processor.convertAndValidate()) {
                response.setIsValid(false);
                response.setMessage((Object)"Is Invalid");
            }
            if (!processor.validateRequest()) {
                response.setIsValid(false);
                response.setMessage((Object)("Error en la estructura del mensaje: " + processor.getValidationMessage()));
                final String responseString = JsonManager.convertObjectToJson((Class)CGWMessageResponse.class, (Object)response);
                return responseString;
            }
            final String ret = processor.proccess(false);
            response.setIsValid(true);
            response.setMessage((Object)("Is Valid: " + ret));
            final String responseString = JsonManager.convertObjectToJson((Class)CGWMessageResponse.class, (Object)response);
            return responseString;
        }
        catch (JsonProcessingException var7) {
            final JsonProcessingException e = var7;
            try {
                e.printStackTrace();
                response.setIsValid(false);
                response.setMessage((Object)("Error en la estructura del mensaje: " + Utilities.getString4Exception((Exception)e)));
                final String responseString = JsonManager.convertObjectToJson((Class)CGWMessageResponse.class, (Object)response);
                return responseString;
            }
            catch (JsonProcessingException var8) {
                var8.printStackTrace();
                return null;
            }
        }
        catch (Exception var9) {
            final Exception e2 = var9;
            try {
                e2.printStackTrace();
                response.setIsValid(false);
                response.setMessage((Object)("Error: " + Utilities.getString4Exception(e2)));
                final String responseString = JsonManager.convertObjectToJson((Class)CGWMessageResponse.class, (Object)response);
                return responseString;
            }
            catch (JsonProcessingException var10) {
                var10.printStackTrace();
                return null;
            }
        }
    }
    
    public Object getUrl(final String message) throws JsonParseException, JsonMappingException, IOException {
        this.setObjectClass((Class)GetUrlRequest.class);
        this.getInstance(message);
        final GetUrlRequest request = (GetUrlRequest)this.getRequest();
        final String newUrl = "http://bitls?qwrwefsd22";
        final GetUrlResponse response = new GetUrlResponse();
        response.setUrl(newUrl);
        this.getInstance(message);
        return JsonManager.convertObjectToJson((Class)GetUrlResponse.class, (Object)response);
    }
    
    public Object getPin(final String message) throws JsonParseException, JsonMappingException, IOException {
        this.setObjectClass((Class)GetPinRequest.class);
        this.getInstance(message);
        final GetPinRequest request = (GetPinRequest)this.getRequest();
        final GetPinResponse response = new GetPinResponse();
        final String plaintext = request.getRequestId();
        String hashtext = null;
        try {
            final MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            final byte[] digest = m.digest();
            final BigInteger bigInt = new BigInteger(1, digest);
            hashtext = bigInt.toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            hashtext = request.getRequestId();
            e.printStackTrace();
        }
        response.setPinId(hashtext);
        return JsonManager.convertObjectToJson((Class)GetPinResponse.class, (Object)response);
    }
}
