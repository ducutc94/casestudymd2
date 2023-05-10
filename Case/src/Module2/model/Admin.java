package Module2.model;

public class Admin {
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public String toString() {
        return
                 username  +
                "," + password  ;
    }
}
