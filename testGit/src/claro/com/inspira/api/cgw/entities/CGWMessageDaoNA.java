// 

// 

package claro.com.inspira.api.cgw.entities;

import claro.com.inspira.api.common.entities.BasicAttach;
import java.util.List;

public class CGWMessageDaoNA extends CGWMessageDao
{
    public List<BasicAttach> attach;
    
    public List<BasicAttach> getAttach() {
        return this.attach;
    }
    
    public void setAttach(final List<BasicAttach> attach) {
        this.attach = attach;
    }
}
