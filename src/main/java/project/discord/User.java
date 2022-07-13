package project.discord;

public class User {
    private String Name;
    private String lName;

    public User(String name, String lName) {
        Name = name;
        this.lName = lName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
