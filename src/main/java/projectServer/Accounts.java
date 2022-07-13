package projectServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class Accounts implements Serializable{
    private String state;
    private String name;
    private String password;
    private String Email;
    private String phoneNumber;
    private ArrayList<String> friends;
    private ArrayList<String> request;
    private ArrayList<String> blocklist;
    public Accounts(String name, String password, String Email, String phoneNumber) {
        request = new ArrayList<>();
        friends = new ArrayList<>();
        blocklist = new ArrayList<>();
        this.name = name;
        this.password = password;
        this.Email = Email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends.add(friends);
    }

    public ArrayList<String> getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request.add(request);
    }

    public ArrayList<String> getBlocklist() {
        return blocklist;
    }

    public void setBlocklist(ArrayList<String> blocklist) {
        this.blocklist = blocklist;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return name.equals(accounts.name);
    }

}
