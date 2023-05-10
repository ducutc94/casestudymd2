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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void display(){
        System.out.printf("%-5s%-10s%s"
                ,this.id, this.name,this.status+"\n");

    }

}
