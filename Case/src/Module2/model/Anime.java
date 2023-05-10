package Module2.model;

import java.io.Serializable;

public class Anime implements Serializable {
    private static final long serialVersionUID = 3L;
    public static int INDEX=0;
    private int id;
    private String username;
    private Actions actions;
    private double price;

    public Anime() {
    }

    public Anime(String username, Actions actions, double price) {
        this.id = ++INDEX;
        this.username = username;
        this.actions = actions;
        this.price = price;
    }

    public Anime(int id, String username, Actions actions, double price) {
        this.id = id;
        this.username = username;
        this.actions = actions;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Actions getAction() {
        return actions;
    }

    public void setAction(Actions actions) {
        this.actions = actions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                 id +
                "," + username +
                "," + actions.getName() +
                "," + price
                ;
    }
    public void display(){
        System.out.printf("%-5s%-15s%-20s%s",
                this.id,this.getUsername(),this.getAction().getName(),this.getPrice()+"\n");
    }
}
