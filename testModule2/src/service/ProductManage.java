package service;

import model.IoFile;
import model.Manage;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductManage implements IoFile<Product>, Manage<Product> {
    private final Scanner scanner;

    private static List<Product> productList;
    private final String PATH= "C:\\Users\\Tien\\Desktop\\Module2\\testModule2\\src\\iofile\\product.txt";

    public ProductManage() {
        this.scanner = new Scanner(System.in);
        productList=read(PATH);
    }

    @Override
    public void write(List<Product> e, String path) {
        File file=new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Product u : productList) {
                bufferedWriter.write( u.getId()+","+u.getName() + ","
                        +u.getPrice()+","+ u.getQuantity() + "," +
                        u.getMother()+ "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public List<Product> read(String path) {
        File file = new File(path);
        ArrayList<Product> product = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                String[] strings = data.split(", ");
                product.add(new Product(Integer.parseInt(strings[0]), strings[1], Double.parseDouble(strings[2]), Integer.parseInt(strings[3]), strings[4]));
            }

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return product;
    }

    @Override
    public void creat() {
        Product p=product();
        productList.add(p);
        write(productList,PATH);
    }

    @Override
    public Product edit() {
        displayAll();
        System.out.println("Enter id : ");
        int id=-1;
        do{
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please number my boy!");
            }
        }while (true);
        int idEdit=getById(id);
        if(idEdit!=-1){
            System.out.println("Enter new name: ");
            String name=scanner.nextLine();
            if(!name.equals("")){
                productList.get(idEdit).setName(name);
            }
            System.out.println("Enter price: ");
            double d=-1;
            do{
                try {
                    d = Double.parseDouble(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please number my boy!");
                }
            }while (true);
            productList.get(idEdit).setPrice(d);
            System.out.println("Enter quantity.");
            int quantity=-1;
            do{
                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please number my boy!");
                }
            }while (true);
            productList.get(idEdit).setQuantity(quantity);
            System.out.println("Enter mother: ");
            String mother=scanner.nextLine();
            productList.get(idEdit).setMother(mother);
            write(productList,PATH);
            return productList.get(idEdit);

        }
        return null;
    }

    @Override
    public Product delete() {
        displayAll();
        System.out.println("Enter id your want delete: ");

        int id=-1;
        do{
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please re-enter the number!");
            }
        }while (true);
        int idDelete=getById(id);
        if(idDelete!=-1){
            System.out.println("You want to delete Y/N");
            String str=scanner.nextLine();
            if(str.equals("Y")){
                Product anime=productList.remove(idDelete);
                write(productList,PATH);
                return anime;
            }else {
                return null;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        System.out.printf("%-5s%-15s%-20s%-20s%s",
                "ID", "NAME", "PRICE", "QUANTITY","MOTHER\n");
        for (Product a:productList) {
            a.display();
        }
    }
    public  Product product(){
        System.out.println("Enter id: ");
        int id=Integer.parseInt(scanner.nextLine());
        System.out.println("Enter name");
        String name=scanner.nextLine();
        System.out.println("Enter pice: ");
        double price=-1.2;
        do{
            try {
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please  number my boy.!");
            }
        }while (true);
        System.out.println("Enter quantity");
        int quantity=Integer.parseInt(scanner.nextLine());
        System.out.println("Enter mother");
        String mother=scanner.nextLine();
        Product p=new Product(id,name,price,quantity,mother);
        return p;
    }
    public int getById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void displayOne(Product a){
        System.out.printf("%-5s%-15s%-20s%-20s%s",
                "ID", "NAME", "PRICE", "QUANTITY","MOTHER\n");
        a.display();
    }
    public Product search(){
        double priceMax= 0;
        int index=0;
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getPrice()>priceMax){
                priceMax=productList.get(i).getPrice();
                index=i;
            }
        }return productList.get(index);
    }
    public void sortUp() {
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() > o2.getPrice()) return 1;
                else if (o1.getPrice() < o2.getPrice()) return -1;
                else return 0;
            }
        });
        displayAll();
    }
    public void sortDown() {
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o2.getPrice() > o1.getPrice()) return 1;
                else if (o2.getPrice() < o1.getPrice()) return -1;
                else return 0;
            }
        });
        displayAll();
    }
}
