package widget;

import java.util.*;

public class Table {

    private ArrayList<String> box;

    public Table() {
        this.box = new ArrayList<>();
    }

    public boolean find(String msg) {
        return box.contains(msg);
    }

    public void addToTable(String msg) {
        if (!find(msg)) box.add(msg);
    }
}

