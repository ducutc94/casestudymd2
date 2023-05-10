package Module2.service;

import java.util.ArrayList;

public interface Manage<E> {
    void creat();
    E edit();
    E delete();
    void displayAll();
}
