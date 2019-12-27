// 

// 

package claro.com.inspira.api.cgw.entities;

import claro.com.inspira.api.common.entities.Attach;
import java.util.List;

public class CGWMessageDaoA extends CGWMessageDao
{
    public List<Attach> attach;
    
    public List<Attach> getAttach() {
        return this.attach;
    }
    
    public void setAttach(final List<Attach> attach) {
        this.attach = attach;
    }
}
