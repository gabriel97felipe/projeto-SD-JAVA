/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemas;

import java.util.List;

/**
 *
 * @author gabri
 */
public class CalculoRetornoDTO {
    private List<String> msgs;
    private boolean status;

    /**
     * @return the msgs
     */
    public List<String> getMsgs() {
        return msgs;
    }

    /**
     * @param msgs the msgs to set
     */
    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
           

    
}
