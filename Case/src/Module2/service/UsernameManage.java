package Module2.service;

import Module2.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UsernameManage implements Manage<User>, IOFile<User> {
    private static final Validate validate = new Validate();
    private final Scanner scanner;
    private static ArrayList<User> users;
    private static UsernameManage instance=null;
    private final String PATH = "C:\\Users\\Tien\\Desktop\\Module2\\Case\\src\\Module2\\IOFile\\User";

    private UsernameManage() {
        this.scanner = new Scanner(System.in);
        users = read(PATH);
        checkDefaultIndex();
    }
    public  synchronized static UsernameManage getInstance(){
        if(instance==null){
            instance=new UsernameManage();
        }
        return instance;
    }

    private void checkDefaultIndex() {
        if (users.isEmpty()) {
            User.INDEX = 0;
        } else {
            User.INDEX = users.get(users.size() - 1).getId();
        }
    }

    @Override
    public void creat() {

    }

    @Override
    public User edit() {
        return null;
    }

    public void up() {
        displayAll();
        System.out.println("Enter id want to up");
        int id=-1;
        do {
            try {
                System.out.println("Input id you want: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Have error, please try again!");
            }
        } while (true);
        int idUp = getById(id);
        if (idUp != -1) {
            users.get(idUp).setRole("admin");
            write(users, PATH);
            System.out.println("Up Success");
        }
    }

    @Override
    public User delete() {
        return null;
    }

    @Override
    public void displayAll() {
        for (User e : users) {
            System.out.println(e);
        }


    }

    public int getById(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void write(ArrayList<User> e, String path) {
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (User u : users) {
                bufferedWriter.write(u.getId() + "," + u.getUsername() + "," + u.getPassword() + "," +
                        u.getGmail() + "," + u.getPhone() + "," + u.getRole() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public ArrayList<User> read(String path) {
        users = new ArrayList<>();
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                String[] strings = data.split(",");
                users.add(new User(Integer.parseInt(strings[0]), strings[1],
                        strings[2], strings[3], strings[4], strings[5]));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return users;
    }

    public User login() {
        read(PATH);
        System.out.println("Enter username: ");
        String name = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        if (validate.validateFullName(name) && validate.validatePassword(password)) {
            for (User user : users) {
                if (user.getUsername().equals(name) && user.getPassword().equals(password) && user.getRole().equals("admin")) {
                    System.out.println("Login Success");
                    return user;
                } else if (user.getUsername().equals(name) && user.getPassword().equals(password) && user.getRole().equals("user")) {
                    System.out.println("Login Success");
                    return user;
                }
            }
        } else {
            System.out.println("Enter again");
        }
        return null;
    }

    public void register() {
        System.out.println("Enter FullName: ");
        String username = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        System.out.println("Enter Gmail: ");
        String gmail = scanner.nextLine();
        System.out.println("Enter Phone: ");
        String phone = scanner.nextLine();
        boolean flag = false;
        for (User u : users) {
            if (username.equals(u.getUsername())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            if (validate.validateFullName(username) && validate.validatePhone(phone)
                    && validate.validatePassword(password) && validate.validateEmail(gmail)) {
                User user = new User(username, password, gmail, phone);
                users.add(user);
                write(users, PATH);
                System.out.println("Register Success");
        } else {
                System.out.println("Validate not okay man.");
            }
        }else {
            System.out.println("List have a username:");
        }
    }

    public void editUser(User user) {
        System.out.println("Enter new name: ");
        String name = scanner.nextLine();
        System.out.println("Enter new password: ");
        String password = scanner.nextLine();
        System.out.println("Enter new gmail: ");
        String gmail = scanner.nextLine();
        System.out.println("Enter new phone: ");
        String phone = scanner.nextLine();
        boolean flag = false;
        for (User u : users) {
            if (name.equals(u.getUsername())) {
                flag = true;
                break;
            }
        }if ((!flag)){
            if (validate.validateFullName(name) && validate.validatePhone(phone)
                    && validate.validatePassword(password) && validate.validateEmail(gmail)) {
                user.setUsername(name);
                user.setPhone(phone);
                user.setGmail(gmail);
                user.setPassword(password);
                write(users, PATH);
                System.out.println("Update Success");
        }else {
                System.out.println("Validate not okay men.");
            }

        }else {
            System.out.println("List have a username:");
        }
    }
}
