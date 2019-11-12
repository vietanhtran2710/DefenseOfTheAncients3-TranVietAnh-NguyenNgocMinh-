package Entity;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<List<Integer>> path;

    public Path() {
        path = new ArrayList<>();
    }

    public List<List<Integer>> getPath() {
        return path;
    }

    public void addItem(List<Integer> newItem) {
        path.add(newItem);
    }
}
