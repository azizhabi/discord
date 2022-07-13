package projectServer;

import java.io.Serializable;
import java.util.ArrayList;

public class massage implements Serializable{

    private String name;
    private String massages;
    private String sended;
    public massage (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMassages() {
        return massages;
    }

    public void setMassages(String massages) {
        this.massages = massages;
    }

    public String getSended() {
        return sended;
    }

    public void setSended(String sended) {
        this.sended = sended;
    }
    

    
}
