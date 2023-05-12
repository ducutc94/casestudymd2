package model;

import java.util.ArrayList;
import java.util.List;

public interface IoFile<E> {
        void write(List<E> e, String path);

        List<E> read(String path);

}
