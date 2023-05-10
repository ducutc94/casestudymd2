package Module2.service;

import Module2.model.Actions;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionManage implements Manage<Actions>, IOFile<Actions> {
    private final Scanner scanner;
    private final ArrayList<Actions> arrayList;
    private final String PATH = "C:\\Users\\Tien\\Desktop\\Module2\\Case\\src\\Module2\\IOFile\\Action";

    public ActionManage(Scanner scanner) {
        this.scanner = scanner;
        arrayList = read(PATH);
        checkDefaultIndex();
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
        System.out.println("Enter id you want to edit: ");
        if (getById() != null) {
            System.out.println("Enter new name: ");
            String name = scanner.nextLine();
            getById().setName(name);
            write(arrayList, PATH);
            return getById();
        }
        return null;
    }

    public Actions getById() {
        displayAll();
        int id;
        do {
            try {
                System.out.println("Input id you want to find: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Have error, please try again!");
            }
        } while (true);

        for (Actions actions : arrayList) {
            if (actions.getId() == id) {
                return actions;
            }
        }
        return null;
    }

    @Override
    public Actions delete() {
        System.out.println("Enter id you want to delete: ");

        if (getById() != null) {
            arrayList.remove(getById());
            write(arrayList, PATH);
            return getById();
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
            System.err.println(ioException.getMessage());
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
            System.err.println(ioException.getMessage());
        }
        return actionsArrayList;
    }
}
