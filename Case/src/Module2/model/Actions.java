package Module2.model;

import java.io.Serializable;

public class Actions implements Serializable {
    private static final long serialVersionUID = 3L;
    public static int INDEX=0;
    private int id;
    private String name;

    public Actions() {
    }

    public Actions(String name) {
        this.id=++INDEX;
        this.name = name;
    }

    public Actions(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return
                 id +
                "," + name ;
    }
    public void  display(){
        System.out.printf("%-15s%s",this.getId(),this.getName()+"\n");
    }
}
