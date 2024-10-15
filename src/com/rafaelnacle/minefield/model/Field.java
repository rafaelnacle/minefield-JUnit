package com.rafaelnacle.minefield.model;

import com.rafaelnacle.minefield.exception.ExplosionException;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final int row;
    private final int column;

    private boolean open;
    private boolean mined;
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

    void toggleFlagging() {
        if(!open) {
            flagged = !flagged;
        }
    }

    boolean open() {
        if (!open && !flagged) {
            open = true;

            if (mined) {
                throw new ExplosionException();
            }

            if (safeNeighbourhood()) {
                neighbours.forEach(Field::open);
            }

            return true;
        } else {
            return false;
        }
    }

    boolean safeNeighbourhood() {
        return neighbours.stream().noneMatch(n -> n.mined);
    }

    void mine() {
        mined = true;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClosed() {
        return !isOpen();
    }
}
