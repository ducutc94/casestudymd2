package Module2.service;

import Module2.InterFace.IOFile;
import Module2.InterFace.Manage;
import Module2.model.Anime;
import Module2.model.Cart;
import Module2.model.CartDetail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Module2.Controller.MainTest.user;

public class CartDetailManage implements IOFile<CartDetail>, Manage<CartDetail> {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<CartDetail> cartDetails;
    private final List<Cart> carts = new ArrayList<>();
    private final AnimeManage animeManage;
    private static CartDetailManage instance = null;

    private final String PATH = "C:\\Users\\Tien\\Desktop\\Module2\\Case\\src\\Module2\\Text\\Cart.txt";

    private CartDetailManage(AnimeManage animeManage) {
        this.animeManage = animeManage;
        cartDetails = read(PATH);
        checkDefaultIndex();
    }

    public synchronized static CartDetailManage getInstance() {
        if (instance == null) {
            instance = new CartDetailManage(AnimeManage.getInstance());
        }
        return instance;
    }

    private void checkDefaultIndex() {
        if (cartDetails.isEmpty()) {
            CartDetail.INDEX = 0;
        } else {
            CartDetail.INDEX = cartDetails.get(cartDetails.size() - 1).getId();
        }
    }


    public void sumCart() {
        double sum = 0;
        for (CartDetail c : cartDetails) {
            if (!c.getCart().isStatus()) {
                sum += c.getAnime().getPrice() * c.getQuantity();
            }
            System.out.println("Sum is: " + sum);
        }
    }

    public void pay() {
        sumCart();
        System.out.println("You want to pay: Y/N");
        String pay = scanner.nextLine();
        if (pay.equalsIgnoreCase("Y")) {
            for (CartDetail c : cartDetails) {
                if (user.getUsername().equals(c.getCart().getName())) {
                    c.getCart().setStatus(true);
                    write(cartDetails, PATH);
                    System.out.println("Thank kiu my Baby..I love you!!!!!<3<3<3");
                }
            }
        } else if (pay.equalsIgnoreCase("N")) {
            System.out.println("Not okay.");
        }
    }

    public CartDetail getById() {
        int idCart;
        do {
            try {
                System.out.println("Input id you want: ");
                idCart = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Have error, please try again!");
            }
        } while (true);
        for (CartDetail cartDetail : cartDetails) {
            if (idCart == cartDetail.getId() && cartDetail.getCart().getName().equals(user.getUsername())) {
                return cartDetail;
            }
        }
        return null;
    }

    @Override
    public void write(ArrayList<CartDetail> cartDetails, String path) {
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(cartDetails);
            output.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Error!!!");
        }
    }

    @Override
    public ArrayList<CartDetail> read(String path) {
        File file = new File(path);
        ArrayList<CartDetail> cartDetailArrayList = new ArrayList<>();
        try (ObjectInputStream objectInputStream
                     = new ObjectInputStream(new FileInputStream(file))) {
            cartDetailArrayList = (ArrayList<CartDetail>) objectInputStream.readObject();
        } catch (Exception ioException) {
            System.out.println("bye");
        }
        return cartDetailArrayList;
    }

    @Override
    public void creat() {
        String UserName = user.getUsername();
        Cart cart = new Cart(UserName);
        for (Cart i : carts) {
            if (cart.getName().equals(UserName) && !i.isStatus()) {
                cart = i;
            }
        }
        animeManage.displayAll();
        System.out.println("Enter id your choice:");
        int choice = -1;
        do {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please re-enter the number!");
            }
        } while (true);
        Anime a = animeManage.getByIdToAnime(choice);
        System.out.println("Enter the quantity you want to buy:");
        int quantity = Integer.parseInt(scanner.nextLine());
        boolean flag = true;
        int index = -1;
        for (int i = 0; i < cartDetails.size(); i++) {
            if (user.getUsername().equals(cartDetails.get(i).getCart().getName()) && cartDetails.get(i).getAnime().getUsername().equals(a.getUsername())) {
                index = i;
                flag = false;
            }
        }
        if (!flag) {
            int quantity1 = cartDetails.get(index).getQuantity();
            cartDetails.get(index).setQuantity(quantity1 + quantity);
        } else {
            CartDetail cartDetail = new CartDetail(cart, a, quantity);
            cartDetails.add(cartDetail);
        }
        write(cartDetails, PATH);
    }

    @Override
    public CartDetail edit() {
        CartDetail cartDetail = getById();
        if (cartDetail != null) {
            System.out.println("Enter Change Anime!");
            animeManage.displayAll();
            System.out.println("Enter id your choice:");
            int id = Integer.parseInt(scanner.nextLine());
            cartDetail.setAnime(animeManage.getByIdToAnime(id));
            System.out.println("Change quantity!");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity != 0) {
                cartDetail.setQuantity(quantity);
            }
            return cartDetail;
        }
        write(cartDetails, PATH);
        return null;
    }

    @Override
    public CartDetail delete() {
        CartDetail cartDetail = getById();
        if (cartDetail != null) {
            cartDetails.remove(cartDetail);
            System.out.println("Removed from cart !");
            return cartDetail;
        }
        write(cartDetails, PATH);
        return null;
    }

    @Override
    public void displayAll() {
        System.out.printf("%-5s%-15s%-20s%-20s%s", "ID", "USER", "NAME", "PRICE/ONE DAY", "QUANTITY\n");
        for (CartDetail c : cartDetails) {
            if (user.getUsername().equals(c.getCart().getName())) {
                c.display();
            }
        }
    }

}
