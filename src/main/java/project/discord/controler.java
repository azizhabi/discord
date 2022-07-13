package project.discord;

public class controler {
    private static String Username;
    private static String email;
    private static String phone;
    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String username) {
        Username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        controler.email = email;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        controler.phone = phone;
    }
}
