// 

// 

package claro.com.inspira.data;

import com.mongodb.util.JSON;
import com.mongodb.BasicDBObjectBuilder;
import claro.com.inspira.api.cgw.data.CGWPersisterDataMgr;
import claro.com.inspira.json.util.JsonManager;
import org.mongojack.DBCursor;
import java.util.List;
import com.mongodb.QueryBuilder;
import java.util.ArrayList;
import com.mongodb.BasicDBObject;
import claro.com.inspira.api.cgw.entities.CGWMessageGetRequest;
import com.mongodb.DBObject;
import org.mongojack.WriteResult;
import claro.com.inspira.api.cgw.entities.CGWMessageRequest;
import org.mongojack.JacksonDBCollection;
import java.net.UnknownHostException;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.util.Map;

public class MongoDbUtilityMgr
{
    Map map;
    DBCollection collection;
    DB db;
    MongoClient mongo;
    
    public MongoDbUtilityMgr() {
        this.map = null;
    }
    
    public void connect(final Map map) throws UnknownHostException {
        this.map = map;
        this.mongo = new MongoClient((String)map.get("dbHost"), (int)map.get("dbPort"));
        this.db = this.mongo.getDB((String)map.get("dbName"));
        this.collection = this.db.getCollection((String)map.get("dbCollectionName"));
    }
    
    public void close() {
        this.mongo.close();
    }
    
	public boolean write(Object toPersist, Class toWriteClass) {
		JacksonDBCollection<CGWMessageRequest, String> jacksonCollection = JacksonDBCollection.wrap(collection, toWriteClass,
		        String.class);
		org.mongojack.WriteResult<CGWMessageRequest, String> result = jacksonCollection.insert((CGWMessageRequest)toPersist);
		return true;
	}
    
    public void write(final DBObject toPersist) {
        final com.mongodb.WriteResult result = this.collection.insert(new DBObject[] { toPersist });
        System.out.println(result.getUpsertedId());
        System.out.println(result.getN());
        System.out.println(result.isUpdateOfExisting());
    }
    
    public DBObject getQuery(final CGWMessageGetRequest request) {
        final DBObject query = (DBObject)new BasicDBObject();
        final QueryBuilder queryBuilder = null;
        final List<BasicDBObject> andQuery = new ArrayList<BasicDBObject>();
        if (request.getIdMessage() != null) {
            andQuery.add(new BasicDBObject("idMessage", (Object)new BasicDBObject("$in", (Object)request.getIdMessage())));
        }
        if (request.getProfileId() != null) {
            andQuery.add(new BasicDBObject("profileId", (Object)request.getProfileId()));
        }
        if (request.getContentType() != null) {
            andQuery.add(new BasicDBObject("contentType", (Object)request.getContentType()));
        }
        if (request.getState() != null) {
            andQuery.add(new BasicDBObject("state", (Object)request.getState()));
        }
        if (request.getStateDate() != null) {
            andQuery.add(new BasicDBObject("stateDate", (Object)request.getState()));
        }
        if (request.getCommunicationOrigin() != null) {
            andQuery.add(new BasicDBObject("communicationOrigin", (Object)new BasicDBObject("$in", (Object)request.getCommunicationOrigin())));
        }
        if (request.getCommunicationType() != null) {
            andQuery.add(new BasicDBObject("communicationType", (Object)new BasicDBObject("$in", (Object)request.getCommunicationType())));
        }
        if (request.getMessageChannel() != null) {
            andQuery.add(new BasicDBObject("messageBox.messageChannel", (Object)request.getMessageChannel()));
        }
        if (request.getCustomerId() != null) {
            andQuery.add(new BasicDBObject("messageBox.messageBox.customerId", (Object)request.getCustomerId()));
        }
        if (request.getMessageBox() != null) {
            andQuery.add(new BasicDBObject("messageBox.messageBox.customerBox", (Object)new BasicDBObject("$in", (Object)request.getMessageBox())));
        }
        if (request.getAttach() != null) {
            if (request.getAttach().getName() != null) {
                andQuery.add(new BasicDBObject("attach.name", (Object)request.getAttach().getName()));
            }
            if (request.getAttach().getEncode() != null) {
                andQuery.add(new BasicDBObject("attach.encode", (Object)request.getAttach().getEncode()));
            }
            if (request.getAttach().getType() != null) {
                andQuery.add(new BasicDBObject("attach.type", (Object)request.getAttach().getType()));
            }
        }
        request.getPage();
        query.put("$and", (Object)andQuery);
        final String queryString = query.toString();
        return query;
    }
    
    public DBCursor<Object> read(final Class toReadClass, final CGWMessageGetRequest request) {
        final JacksonDBCollection<Object, String> jacksonCollection = (JacksonDBCollection<Object, String>)JacksonDBCollection.wrap(this.collection, toReadClass, (Class)String.class);
        final DBCursor<Object> cursor = (DBCursor<Object>)jacksonCollection.find(this.getQuery(request));
        return cursor;
    }
    
    public static void main(final String[] args) {
        try {
            final CGWMessageGetRequest request = new CGWMessageGetRequest();
            final List<String> messageIds = new ArrayList<String>();
            messageIds.add("20181007122345");
            messageIds.add("20181007122643");
            request.setIdMessage(messageIds);
            System.out.println(JsonManager.convertObjectToJson((Class)CGWMessageGetRequest.class, (Object)request));
            final CGWPersisterDataMgr mgr = new CGWPersisterDataMgr();
            mgr.connect();
            mgr.getMessage(request);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private DBObject createDBObject4Json(final String toPersist) {
        final BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        final DBObject object = (DBObject)JSON.parse(toPersist);
        return object;
    }
    
    public void update(final Object toUpdate, final DBObject doc) {
    }
    
    public void delete(final Object toDelete, final DBObject doc) {
    }
}
