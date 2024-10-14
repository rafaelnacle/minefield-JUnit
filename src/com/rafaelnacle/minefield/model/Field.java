package com.rafaelnacle.minefield.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final int row;
    private final int column;

    private boolean open;
    private boolean undermined;
    private boolean flagged;

    private List<Field> neighbours = new ArrayList<>();

    Field(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
