package model;

public class Product {
private int id;
private String name;
private double price;
private int quantity;
private String mother;

    public Product() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public Product(int id, String name, double price, int quantity, String mother) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.mother = mother;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", mother='" + mother + '\'' +
                '}';
    }
    public void display(){
        System.out.printf("%-5s%-15s%-20s%-20s%s",
                this.id,this.name,this.price,this.quantity,this.mother+"\n");
    }
}
