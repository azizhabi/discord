package projectServer;

import java.io.Serializable;
import java.util.ArrayList;

public class TheServer implements Serializable {
    private String ServerName;
    private String ServerCreater;
    private ArrayList<String> members;
    private ArrayList<String> GroupCreater;
    private ArrayList<String> GroupDeleter;
    private ArrayList<String> UserDeleter;
    private ArrayList<String> UserRestricter;
    private ArrayList<String> ShowMassages;
    private ArrayList<String> Massagepiner;
    private ArrayList<String> Admins;
    private ArrayList<Group> groups; 
    public TheServer(String ServerName, String ServerCreater) {
        this.ServerName = ServerName;
        this.ServerCreater = ServerCreater;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public ArrayList<String> getAdmins() {
        return Admins;
    }

    public void setAdmins(String Admins) {
        this.Admins.add(Admins);
    }
    
    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(Group groups) {
        this.groups.add(groups);
    }

    public String getServerCreater() {
        return ServerCreater;
    }

    public void setServerCreater(String ServerCreater) {
        this.ServerCreater = ServerCreater;
    }

    public String getServerName() {
        return ServerName;
    }

    public void setServerName(String ServerName) {
        this.ServerName = ServerName;
    }

    public ArrayList<String> getGroupCreater() {
        return GroupCreater;
    }

    public void setGroupCreater(String GroupCreater) {
        this.GroupCreater.add(GroupCreater);
    }

    public ArrayList<String> getGroupDeleter() {
        return GroupDeleter;
    }

    public void setGroupDeleter(String GroupDeleter) {
        this.GroupDeleter.add(GroupDeleter);
    }

    public ArrayList<String> getUserDeleter() {
        return UserDeleter;
    }

    public void setUserDeleter(String UserDeleter) {
        this.UserDeleter.add(UserDeleter);
    }

    public ArrayList<String> getUserRestricter() {
        return UserRestricter;
    }

    public void setUserRestricter(String UserRestricter) {
        this.UserRestricter.add(UserRestricter);
    }

    public ArrayList<String> getShowMassages() {
        return ShowMassages;
    }

    public void setShowMassages(String ShowMassages) {
        this.ShowMassages.add(ShowMassages);
    }

    public ArrayList<String> getMassagepiner() {
        return Massagepiner;
    }

    public void setMassagepiner(String Massagepiner) {
        this.Massagepiner.add(Massagepiner);
    }

}
