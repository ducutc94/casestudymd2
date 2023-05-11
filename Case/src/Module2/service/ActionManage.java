package Module2.service;

import Module2.InterFace.IOFile;
import Module2.InterFace.Manage;
import Module2.model.Actions;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionManage implements Manage<Actions>, IOFile<Actions> {
    private final Scanner scanner;
    private final ArrayList<Actions> arrayList;
    private static ActionManage instance=null;
    private final String PATH = "C:\\Users\\Tien\\Desktop\\Module2\\Case\\src\\Module2\\IOFile\\Action";

    private ActionManage() {
        this.scanner = new Scanner(System.in);
        arrayList = read(PATH);
        checkDefaultIndex();
    }
    public  synchronized static ActionManage getInstance(){
        if(instance==null){
            instance=new ActionManage();
        }
        return instance;
    }

    private void checkDefaultIndex() {
        if (arrayList.isEmpty()) {
            Actions.INDEX = 0;
        } else {
            Actions.INDEX = arrayList.get(arrayList.size() - 1).getId();
        }
    }

    @Override
    public void creat() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        arrayList.add(new Actions(name));
        write(arrayList, PATH);
    }

    @Override
    public Actions edit() {
        displayAll();
        System.out.println("Enter id want to edit : ");
        int id=-1;
        do{
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please re-enter the number!");
            }
        }while (true);
        if (getById(id) != null) {
            System.out.println("Enter new name: ");
            String name = scanner.nextLine();
            getById(id).setName(name);
            write(arrayList, PATH);
            return getById(id);
        }
        return null;
    }

    public Actions getById(int id) {
        for (Actions actions : arrayList) {
            if (actions.getId() == id) {
                return actions;
            }
        }
        return null;
    }
    @Override
    public Actions delete() {
        displayAll();
        System.out.println("Enter id you want to delete: ");
        int id=-1;
        do{
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please  number my boy!");
            }
        }while (true);
        if (getById(id) != null) {
            arrayList.remove(getById(id));
            write(arrayList, PATH);
            return getById(id);
        }
        return null;
    }
    @Override
    public void displayAll() {
        System.out.printf("%-15s%s", "ID", "NAME\n");
        for (Actions e : arrayList) {
            e.display();
        }
    }
    @Override
    public void write(ArrayList<Actions> e, String path) {
        File file = new File(path);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Actions actions : e) {
                bufferedWriter.write(actions.getId() + "," + actions.getName() + "\n");
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
    @Override
    public ArrayList<Actions> read(String path) {
        File file = new File(path);
        ArrayList<Actions> actionsArrayList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                String[] strings = data.split(",");
                actionsArrayList.add(new Actions(Integer.parseInt(strings[0]), strings[1]));
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return actionsArrayList;
    }
}
