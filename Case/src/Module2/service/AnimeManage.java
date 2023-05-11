package Module2.service;

import Module2.InterFace.IOFile;
import Module2.InterFace.Manage;
import Module2.model.Actions;
import Module2.model.Anime;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimeManage implements IOFile<Anime>, Manage<Anime> {

    private final Scanner scanner;

    private static ArrayList<Anime> animeArrayList;
    private final ActionManage actionManage;
    private static AnimeManage instance=null;
    private final String PATH= "C:\\Users\\Tien\\Desktop\\Module2\\Case\\src\\Module2\\IOFile\\Anime";

    private AnimeManage(ActionManage actionManage) {
        this.scanner=new Scanner(System.in);
        this.actionManage=actionManage;
        animeArrayList=read(PATH);
        checkDefaultIndex();
    }
    public  synchronized static AnimeManage getInstance(){
        if(instance==null){
            instance=new AnimeManage(ActionManage.getInstance());
        }
        return instance;
    }
    private void checkDefaultIndex() {
        if (animeArrayList.isEmpty()) {
            Anime.INDEX = 0;
        } else {
            Anime.INDEX = animeArrayList.get(animeArrayList.size() - 1).getId();
        }
    }
    @Override
    public void write(ArrayList<Anime> e, String path) {
        File file=new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Anime u : animeArrayList) {
                bufferedWriter.write( u.getId()+","+u.getUsername() + ","
                        +u.getAction().getId()+","+ u.getAction().getName() + "," +
                        u.getPrice()+ "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public ArrayList<Anime> read(String path) {
        animeArrayList=new ArrayList<>();
        File file=new File(path);
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String data;
            while ((data=bufferedReader.readLine())!=null){
                String[] strings=data.split(",");
                animeArrayList.add(new Anime(Integer.parseInt(strings[0]), strings[1],
                        new Actions(Integer.parseInt(strings[2]),strings[3]), Double.parseDouble(strings[4])));
            }
            bufferedReader.close();
            fileReader.close();
        }catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
        return animeArrayList;
    }

    @Override
    public void creat() {
        System.out.println("Enter username: ");
        String username=scanner.nextLine();
        System.out.println("Enter action your choice");
        Actions actions=getAction();
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
        animeArrayList.add(new Anime(username,actions,price));
        write(animeArrayList,PATH);

    }

    private Actions getAction() {
        actionManage.displayAll();
        System.out.println("Input action: ");
        int id=-1;
        do{
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please  number my boy!");
            }
        }while (true);
        Actions actions;
        do {
            actions = actionManage.getById(id);
        } while (actions == null);
        return actions;
    }

    @Override
    public Anime edit() {
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
                animeArrayList.get(idEdit).setUsername(name);
            }
            System.out.println("Enter new action: ");
            Actions actions=getAction();
            animeArrayList.get(idEdit).setAction(actions);
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
            animeArrayList.get(idEdit).setPrice(d);
            System.out.println("Edit okay.");
            write(animeArrayList,PATH);
            return animeArrayList.get(idEdit);
        }
        return null;
    }
    public int getById(int id) {
        for (int i = 0; i < animeArrayList.size(); i++) {
            if (animeArrayList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    public Anime getByIdToAnime(int id){
        for (Anime anime : animeArrayList) {
            if (anime.getId() == id) {
                return anime;
            }
        }
        return null;
    }

    @Override
    public Anime delete() {
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
            Anime anime=animeArrayList.remove(idDelete);
            write(animeArrayList,PATH);
            return anime;
        }
        return null;
    }

    @Override
    public void displayAll() {
        read(PATH);
        System.out.printf("%-5s%-15s%-20s%s",
                "ID", "NAME", "ACTION", "PRICE/One Day\n");
        for (Anime a:animeArrayList) {
            a.display();
        }
    }
    public void displayOne(Anime a){
        System.out.printf("%-5s%-15s%-20s%s",
                "ID", "NAME", "ACTION", "PRICE/One Day\n");
        a.display();
    }
}
