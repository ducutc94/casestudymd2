package Module2.Controller;

import Module2.model.Anime;
import Module2.model.CartDetail;
import Module2.model.User;
import Module2.service.*;

import java.util.Scanner;

public class MainTest {
    static Scanner scanner = new Scanner(System.in);
    public static User user;

    public static void main(String[] args) {
        ActionManage actionManage = ActionManage.getInstance();
        AnimeManage animeManage = AnimeManage.getInstance();
        UsernameManage usernameManage = UsernameManage.getInstance();
        CartDetailManage cartDetailManage = CartDetailManage.getInstance();
        menuLogin(usernameManage, scanner, animeManage, actionManage, cartDetailManage);
    }

    private static void menuAdmin(AnimeManage animeManage, Scanner scanner, UsernameManage usernameManage, ActionManage actionManage) {

        int choice = -1;
        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("   Have a good night to : " + user.getUsername());
            System.out.println("1.Creat anime");
            System.out.println("2.Edit");
            System.out.println("3.Delete");
            System.out.println("4.DisplayAll");
            System.out.println("5.Action");
            System.out.println("6.Up");
            System.out.println("8.Logout");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please re-enter the number!");
            }
            switch (choice) {
                case 1:
                    animeManage.creat();
                    break;
                case 2:
                    Anime anime = animeManage.edit();
                    animeManage.displayOne(anime);
                    break;
                case 3:
                    Anime anime1 = animeManage.delete();
                    animeManage.displayOne(anime1);
                    break;
                case 4:
                    animeManage.displayAll();
                    break;
                case 5:
                    actionMenu(actionManage, scanner);
                    break;
                case 6:
                    usernameManage.up();
                    break;
            }
        } while (choice != 8);

    }

    private static void menuLogin(UsernameManage usernameManage, Scanner scanner, AnimeManage animeManage, ActionManage actionManage, CartDetailManage cartDetailManage) {
        int choice = -1;
        do {
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("0.Exit");
            System.out.println("Enter choice: ");
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please re-enter the number!");
                }
            } while (true);
            switch (choice) {
                case 1:
                    user = usernameManage.login();
                    if (user != null) {
                        if (user.getRole().equals("admin")) {
                            menuAdmin(animeManage, scanner, usernameManage, actionManage);
                        } else if (user.getRole().equals("user")) {
                            menuUser(animeManage, scanner, usernameManage, cartDetailManage);
                        }
                    } else {
                        System.out.println("Mother");
                    }

                    break;
                case 2:
                    usernameManage.register();
                    break;
                case 0:
                    System.exit(0);
            }

        } while (true);

    }

    private static void menuUser(AnimeManage animeManage, Scanner scanner, UsernameManage usernameManage, CartDetailManage cartDetailManage) {

        int choice = -1;
        do {
            System.out.println("-----lady and gentlemen-----");
            System.out.println("   Hello   " + user.getUsername());
            System.out.println("1.DisplayAll");
            System.out.println("2.Edit profile");
            System.out.println("3.Carts");
            System.out.println("4.Logout");
            System.out.println("Enter your choice");
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please re-enter the number!");
                }
            } while (true);
            switch (choice) {
                case 1:
                    animeManage.displayAll();
                    break;
                case 2:
                    usernameManage.editUser(user);
                    break;
                case 3:
                    menuCart(scanner, cartDetailManage);
                    break;

                case 0:
                    System.exit(0);
                default:
                    System.out.println("Mother");
            }
        } while (choice != 4);

    }

    private static void actionMenu(ActionManage actionManage, Scanner scanner) {
        int choice = -1;
        do {
            System.out.println("------------------------------------");
            System.out.println("   Hello  my boy :" + user.getUsername());
            System.out.println("1.Creat");
            System.out.println("2.Edit");
            System.out.println("3.Delete");
            System.out.println("4.Display");
            System.out.println("0.Exit");
            System.out.println("Enter your choice");
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please re-enter the number!");
                }
            } while (true);
            switch (choice) {
                case 1:
                    actionManage.creat();
                    break;
                case 2:
                    actionManage.edit();
                    break;
                case 3:
                    actionManage.delete();
                    break;
                case 4:
                    actionManage.displayAll();
                    break;
            }
        } while (choice != 0);

    }

    private static void menuCart(Scanner scanner, CartDetailManage cartDetailManage) {
        int choice = -1;
        do {
            System.out.println("Menu");
            System.out.println("1. Display cart");
            System.out.println("2. Add to cart");
            System.out.println("3. Delete from cart");
            System.out.println("4. Total Paid");
            System.out.println("0. Exit!");
            System.out.println("-->Enter your choice here!<--");
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please re-enter the number!");
                }
            } while (true);

            switch (choice) {
                case 1:
                    cartDetailManage.displayAll();
                    break;
                case 2:
                    cartDetailManage.creat();
                    break;
                case 3:
                    CartDetail cartDetail =cartDetailManage.delete();
                    cartDetail.display();
                    break;
                case 4:cartDetailManage.pay();

                    break;
            }
        } while (choice != 0);
    }

}
