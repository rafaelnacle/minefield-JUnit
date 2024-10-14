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

    boolean addNeighbour(Field neighbour) {
        boolean differentRow = row != neighbour.row;
        boolean differentColumn = column != neighbour.column;
        boolean diagonal = differentRow && differentColumn;

        int deltaRow = Math.abs(row - neighbour.row);
        int deltaColumn = Math.abs(column - neighbour.column);
        int deltaMain = deltaColumn + deltaRow;

        if (deltaMain == 1 && !diagonal) {
            neighbours.add(neighbour);
            return true;
        } else if (deltaMain == 2 && diagonal) {
            neighbours.add(neighbour);
            return true;
        } else {
            return false;
        }
    }
}
