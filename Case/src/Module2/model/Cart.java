package Module2.model;
import java.io.Serializable;

public class Cart  implements Serializable {
    private static final long serialVersionUID = 3L;
    private String name;
    private int id;
    private boolean status;

    public Cart(String name) {
        this.name = name;
        this.status=false;
    }

    public Cart() {
    }


    public String getName() {
        return name;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                id +
                "," + name+
                ","+status;
    }
}
