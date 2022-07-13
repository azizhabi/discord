package projectServer;

import java.io.Serializable;

public class groupmassage implements Serializable{ 
   private String groupName;
   private String name;
   private String massage;

    public groupmassage(String groupName, String name, String massage) {
        this.groupName = groupName;
        this.name = name;
        this.massage = massage;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
    
}
