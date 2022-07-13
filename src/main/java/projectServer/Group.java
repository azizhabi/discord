package projectServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Group implements Serializable{
    private String name;
    private String Creater;
    private ArrayList<String> members;
    private ArrayList< String> massages;
    private ArrayList<String> pinmassages;
    public Group (String name, String Creater) {
        
    }

    public String getCreater() {
        return Creater;
    }

    public void setCreater(String Creater) {
        this.Creater = Creater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members.add(members);
    }

    public ArrayList<String> getMassages() {
        return massages;
    }

    public void setMassages(String massages) {
        this.massages.add(massages);
    }

    public ArrayList<String> getPinmassages() {
        return pinmassages;
    }

    public void setPinmassages(String pinmassages) {
        this.pinmassages.add(pinmassages);
    }
    
}
