package Module2.InterFace;

import java.util.ArrayList;

public interface IOFile<E> {
    void write(ArrayList<E> e, String path);

    ArrayList<E> read(String path);
}
