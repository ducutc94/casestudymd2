package Module2.model;

import java.io.Serializable;
import static Module2.Controller.MainTest.user;

public class CartDetail implements Serializable {
    private static final long serialVersionUID = 3L;

    public static int INDEX=0;
    private final int id;
    private final Cart cart;
    private Anime anime;
    private int quantity;

    public CartDetail( Cart cart, Anime anime, int quantity) {
        this.id = ++INDEX;
        this.cart = cart;
        this.anime = anime;
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public int getId() {
        return id;
    }


    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return
                id +
                "," +
                "," + anime +
                "," + quantity +
                '}';
    }
    public void display(){
        System.out.printf("%-5s%-15s%-20s%-20s%s"
                ,this.id,user.getUsername(),this.anime.getUsername(),this.anime.getPrice(),this.quantity+"\n");
    }
}
