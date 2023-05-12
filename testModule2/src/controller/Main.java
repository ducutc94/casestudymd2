package controller;

import model.Product;
import service.ProductManage;

import java.util.Scanner;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        ProductManage productManage=new ProductManage();
        do{

            System.out.println("----CHƯƠNG TRÌNH QUẢN LÍ SẢN PHẨM----");
            System.out.println("Chọn Chức năng theo số(để tiếp tục)");
            System.out.println("1.Xem danh sách");
            System.out.println("2.Thêm mới");
            System.out.println("3.Cập nhật");
            System.out.println("4.Xóa");
            System.out.println("5.Sắp xếp");
            System.out.println("6.Tìm sản phẩm có giá trị đắt nhất");
            System.out.println("7.Đọc từ file");
            System.out.println("8.Ghi vào file");
            System.out.println("9.Thoát");
            int choice=Integer.parseInt(scanner.nextLine());

            switch (choice){
       case 1:productManage.displayAll();
                    break;
                case 2:productManage.creat();
                    break;
                case 3:
                    Product p=productManage.edit();
                    if(p!=null){
                        productManage.displayOne(p);
                    }
                    break;
                case 4:
                    Product p1=productManage.delete();
                    if(p1!=null){
                        productManage.displayOne(p1);
                    }

                    break;
                case 5:
                    sort(productManage);
                    break;
                case 6:Product p2=productManage.search();
                    System.out.println("Sẩn phẩm có giá cao nhất là:");
                productManage.displayOne(p2);
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:System.exit(0);
                    break;
            }
        }while (true);
    }
    public static void sort(ProductManage productManage) {
        int choice1=-1;
        do{
            System.out.println("1.Tăng dần");
            System.out.println("2.Giảm dần");
            System.out.println("3.Thoát");

            do {
                try {
                    choice1=Integer.parseInt(scanner.nextLine());
                    break;
                }catch (NumberFormatException io){
                    System.out.println(io.getMessage());
                }
            }while (true);
            switch (choice1){
                case 1:
                    productManage.sortUp();
                    break;
                case 2:
                    productManage.sortDown();
                    break;
                case 0:
                    System.exit(0);
            }
        }while (choice1!=3);

    }

    }
