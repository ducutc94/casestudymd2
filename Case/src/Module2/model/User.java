package Module2.model;

public class User {
    public static  int INDEX;
    private final int id;
    private String username;
    private String password;
    private String gmail;
    private String phone;
    private String role ;

    public User(String username, String password, String gmail, String phone) {
        this.id = ++INDEX;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.phone = phone;
        this.role="user";
    }

    public User(int id, String username, String password, String gmail, String phone, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.phone = phone;
        this.role = role;
    }

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return
                id +
                        "," + username +
                        "," + password +
                        "," + gmail +
                        "," + phone +
                        "," + role
                ;
    }
}
