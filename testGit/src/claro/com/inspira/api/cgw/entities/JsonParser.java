// 

// 

package claro.com.inspira.api.cgw.entities;

import com.framework.util.ReflectionUtil2;
import java.util.Vector;

public class JsonParser
{
    public Vector nullsFinded;
    public Vector numericsFinded;
    
    public JsonParser() {
        this.nullsFinded = new Vector();
        this.numericsFinded = new Vector();
    }
    
    public boolean validateNotNull(final Vector<String> notNullFields, final Object toValidate, final String className) throws Exception {
        notNullFields.forEach(n -> {
            try {
                this.reviewNotNull(n, toValidate, className);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        });
        return this.nullsFinded.size() < 0;
    }
    
    public void reviewNotNull(final String field, final Object toValidate, final String className) throws Exception {
        final Object returned = ReflectionUtil2.invoke(toValidate, "get" + field, (Object[])null);
        if (returned == null) {
            this.nullsFinded.add(field);
        }
    }
    
    public Vector getNumericsFinded() {
        return this.nullsFinded;
    }
    
    public void setNumericsFinded(final Vector numericsFinded) {
        this.nullsFinded = numericsFinded;
    }
    
    public Vector getNullsFinded() {
        return this.nullsFinded;
    }
    
    public void setNullsFinded(final Vector nullsFinded) {
        this.nullsFinded = nullsFinded;
    }
}
